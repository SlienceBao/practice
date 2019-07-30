package com.jj0327.practice.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jj0327.practice.domain.HouseSale;
import com.jj0327.practice.entity.base.Result;
import com.jj0327.practice.service.SeleniumService;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.jj0327.practice.constant.SouFangConstants.LoginFields;
import static com.jj0327.practice.constant.SouFangConstants.SaleInputFields;

/**
 * @author jinbao
 * @date 2019/7/8 14:08
 * @description:
 */
@Service
public class SeleniumServiceImpl implements SeleniumService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public Result<String> login(String username, String password) {
        WebDriver driver = null;
        try {
            driver = init(1);
            driver.manage().window().maximize();
            //全局隐式等待，等待
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            //设定网址
            driver.get(LoginFields.LOGIN_URL);

            driver.findElement(By.id(LoginFields.LOGIN_BUTTON_ID)).click();

            driver.findElement(By.cssSelector(LoginFields.ACCOUNT_LOGIN_CSS)).click();

            driver.findElement(By.id(LoginFields.USERNAME_ID)).sendKeys(username);
            Thread.sleep(1000);
            driver.findElement(By.id(LoginFields.PASSWORD_ID)).sendKeys(password);
            Thread.sleep(1000);

            WebElement loginButton3 = driver.findElement(By.id(LoginFields.LOGIN_SUBMIT_ID));
            loginButton3.click();
            //显示等待控制对象
            Thread.sleep(1000);
            driver.get("");
//        String page = driver.getPageSource();//获取网页全部信息
            Set<Cookie> cookies = driver.manage().getCookies();
            String s = JSONObject.toJSONString(cookies);
            stringRedisTemplate.opsForValue().set("soufang:cookie:" + username, s);
            return Result.success("OK", s);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(-1, e.getMessage());
        } finally {
            if (driver != null) {
//                driver.close();
            }
        }
    }


    @Override
    public Result publish(String username, String password, HouseSale houseSale) {
        String s = stringRedisTemplate.opsForValue().get("soufang:cookie:" + username);
        if (StringUtils.isBlank(s)) {
            System.out.println("=======================重新登录=========================");
            synchronized (SeleniumService.class) {
                Result<String> login = login(username, password);
                if (login.getCode() != Result.SUCCESS_CODE) {
                    return login;
                }
                s = login.getData();
            }
        }

        WebDriver driver = null;
        try {
            driver = init(2);
            //设定网址
            driver.get(LoginFields.LOGIN_URL);
            List<Cookie> cookies1 = JSON.parseArray(s, Cookie.class);
            for (Cookie cookie : cookies1) {
                driver.manage().addCookie(cookie);
            }
            Thread.sleep(1000);

            driver.get(SaleInputFields.SALE_INPUT_URL);
            Thread.sleep(1000);

//            WebElement element = driver.findElement(By.className("layui-layer-btn0"));

            WebElement text = driver.findElement(By.id(SaleInputFields.BUILDING_ID));
            text.sendKeys(houseSale.getBuildingName());
            Thread.sleep(1000);
            text.sendKeys(Keys.ENTER);
            Thread.sleep(1000);
            // 产权年限
            ((JavascriptExecutor) driver).executeScript(String.format("document.getElementById('%s').style.display='block';", SaleInputFields.PROPERTY_YEAR_ID));
            switch (houseSale.getPropertyYear()) {
                case 40:
                    driver.findElement(By.linkText("40年")).click();
                    driver.findElement(By.linkText("40年")).click();
                    break;
                case 50:
                    driver.findElement(By.linkText("50年")).click();
                    driver.findElement(By.linkText("50年")).click();
                    break;
                case -1:
                    driver.findElement(By.linkText("永久产权")).click();
                    driver.findElement(By.linkText("永久产权")).click();
                    break;
                default:
                    driver.findElement(By.linkText("70年")).click();
                    driver.findElement(By.linkText("70年")).click();
                    break;
            }
            Thread.sleep(1000);

            ((JavascriptExecutor) driver).executeScript(String.format("document.getElementById('%s').style.display='block';", SaleInputFields.PROPERTY_TYPE_ID));
            driver.findElement(By.linkText("商品房")).click();
            Thread.sleep(1000);

            ((JavascriptExecutor) driver).executeScript(String.format("document.getElementById('%s').style.display='block';", SaleInputFields.PROPERTY_SUB_YEAR_ID));
            switch (houseSale.getHouseType()) {
                case "住宅":
                    driver.findElement(By.linkText("普通住宅")).click();
                    break;
                case "公寓":
                    driver.findElement(By.linkText("公寓")).click();
                    break;
                default :
                    driver.findElement(By.linkText("普通住宅")).click();
                    break;
            }
            Thread.sleep(1000);

            driver.findElement(By.id(SaleInputFields.HOME_COUNT_ID)).sendKeys(houseSale.getShi().toString());
            Thread.sleep(1000);
            WebElement inputHall = driver.findElement(By.id(SaleInputFields.HALL_COUNT_ID));
            inputHall.clear();
            inputHall.sendKeys(houseSale.getHall().toString());
            Thread.sleep(1000);
            WebElement inputToilet = driver.findElement(By.id(SaleInputFields.TOILET_COUNT_ID));
            inputToilet.clear();
            inputToilet.sendKeys(houseSale.getToilet().toString());
            Thread.sleep(1000);
            WebElement inputKitchen = driver.findElement(By.id(SaleInputFields.KITCHEN_COUNT_ID));
            inputKitchen.clear();
            inputKitchen.sendKeys(houseSale.getKitchen().toString());
            Thread.sleep(1000);
            WebElement inputBalcony = driver.findElement(By.id(SaleInputFields.BALCONY_COUNT_ID));
            inputBalcony.clear();
            inputBalcony.sendKeys(houseSale.getBalcony().toString());
            Thread.sleep(1000);
            // 结构: 错层,跃层,复式,开间,平层
            ((JavascriptExecutor) driver).executeScript(String.format("document.getElementById('%s').style.display='block';", SaleInputFields.HOUSE_STRUCTURE_ID));
            driver.findElement(By.linkText("开间")).click();
            Thread.sleep(1000);
            // 装修: 豪华装修,精装修, 中装修, 简装修, 毛坯
            ((JavascriptExecutor) driver).executeScript(String.format("document.getElementById('%s').style.display='block';", SaleInputFields.HOUSE_FITMENT_ID));
            driver.findElement(By.linkText("精装修")).click();
            Thread.sleep(1000);
            // 朝向: 东,南,西,北,东南,西南,西北,东北,南北,东西
            ((JavascriptExecutor) driver).executeScript(String.format("document.getElementById('%s').style.display='block';", SaleInputFields.HOUSE_FORWORD_ID));
            driver.findElement(By.linkText("南北")).click();
            Thread.sleep(1000);
            // 类别: 板楼, 塔楼, 砖房, 砖混, 平房, 钢混, 塔板结合
            ((JavascriptExecutor) driver).executeScript(String.format("document.getElementById('%s').style.display='block';", SaleInputFields.BUILDING_TYPE_ID));
            driver.findElement(By.linkText("塔楼")).click();
            Thread.sleep(1000);
            // 建筑面积
            driver.findElement(By.id("BuildingArea")).sendKeys(houseSale.getBuildingArea());
            Thread.sleep(1000);
            // 使用面积
            driver.findElement(By.id("input_LIVEAREA")).sendKeys(houseSale.getUsedArea());
            Thread.sleep(1000);
            // 楼层
            driver.findElement(By.id("input_FLOOR")).sendKeys("17");
            Thread.sleep(1000);
            // 总层数
            driver.findElement(By.id("input_ALLFLOOR")).sendKeys("27");
            Thread.sleep(1000);
            // 层高
            driver.findElement(By.id("input_n_num_floorHeight")).sendKeys("2.9");
            Thread.sleep(1000);
            // 户室号: 栋/弄/座/号/号楼/胡同
            driver.findElement(By.id("buildingnumber")).sendKeys("10");
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("document.getElementById('options_input_Housebuildingnumber').style.display='block';");
            driver.findElement(By.linkText("栋")).click();
            Thread.sleep(1000);
            // 户室号: 单元/栋/弄/座/号/号楼/胡同
            driver.findElement(By.id("unitnumber")).sendKeys("27");
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("document.getElementById('options_input_Houseunitnumber').style.display='block';");
            driver.findElement(By.linkText("单元")).click();
            Thread.sleep(1000);
            // 户室号: 室
            driver.findElement(By.id("housenumber")).sendKeys("1701");
            Thread.sleep(1000);
            // 建造年代
            driver.findElement(By.id("input_CREATETIME")).sendKeys("2014");
            Thread.sleep(1000);
            // 房本年限: 满五/满二/不满二
            driver.findElement(By.linkText("满五")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("input_PRICE")).sendKeys("209");
            Thread.sleep(1000);
            // 配套设置: 煤气/天然气
            driver.findElement(By.cssSelector("input[value = '煤气/天然气']")).click();
            Thread.sleep(1000);
            // 配套设置: 暖气
            driver.findElement(By.cssSelector("input[value = '暖气']")).click();
            Thread.sleep(1000);
            // 标题
            driver.findElement(By.id("houseTitle")).sendKeys("我是标题我是标题我是标题");
            Thread.sleep(1000);
            // 核心卖点
            driver.findElement(By.id("input_n_str_CONTENT")).sendKeys("我是核心卖点");
            Thread.sleep(1000);
            // 业主心态
            driver.findElement(By.id("input_str_OwnerMentality")).sendKeys("我是业主心态");
            Thread.sleep(1000);
            // 小区配套
            driver.findElement(By.id("input_str_CommunityMatching")).sendKeys("我是小区配套");
            Thread.sleep(1000);
            // 税费信息
            driver.findElement(By.id("input_str_TaxAnalysis")).sendKeys("我是税费信息");
            Thread.sleep(1000);
            // 服务信息
            driver.findElement(By.id("input_str_ServiceIntroduction")).sendKeys("我是服务信息");
            Thread.sleep(1000);
            // 看房时间
            driver.findElement(By.id("input_n_str_LOOKHOUSE1")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("input_n_str_LOOKHOUSE2")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("input_n_str_LOOKHOUSE3")).click();
            Thread.sleep(1000);

            String url = "http://img.518fang.com/img6/518upload/201907/088/963/c5283d3291294df49bffedc591ccc263.jpg";
            // 户型图
            uploadPic(url, driver, "Hfile_3");
            Thread.sleep(1000);
            // 室内图
            ArrayList<String> strings = new ArrayList<>(3);
            strings.add("http://img.518fang.com/img7/518upload/201907/152/115/d66cc9296b0d4285a3c016e2ab7183f5.jpg");
            strings.add("http://img.518fang.com/img8/518upload/201907/795/118/bfe576fcd67f4407983666f15eaec617.jpg");
            strings.add("http://img.518fang.com/img6/518upload/201907/454/190/91e254a9aa7c4410bdfbe4736177f1fb.jpg");
            for (String s1 : strings) {
                uploadPic(s1, driver, "Sfile_1");
                Thread.sleep(1000);
            }
            String url3 = "http://img.518fang.com/img6/518upload/201907/562/655/dcefd6ce99494684824860408e6c7ff1.jpg";
            // 小区图
            uploadPic(url3, driver, "Xfile_2");
            Thread.sleep(1000);
            //显示等待控制对象
//        String page = driver.getPageSource();//获取网页全部信息
            driver.findElement(By.id("chk_promisetruth")).click();
            Thread.sleep(1000);
            // 提交房源信息
            driver.findElement(By.id("agentmainput_salesentry_00")).click();
            WebDriverWait wait = new WebDriverWait(driver, 10, 1);
            Thread.sleep(2000);
            String page = driver.getPageSource();
            if (page.contains("录入成功")) {
                String currentUrl = driver.getCurrentUrl();
                return Result.success(currentUrl);
            }
            String text2 = wait.until((ExpectedCondition<WebElement>) text1 -> text1.findElement(By.className("layui-layer-content"))).getText();
            return Result.fail(400, text2);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(-1, e.getMessage());
        } finally {
            if (driver != null) {
//                driver.quit();
            }
        }
    }

    private void uploadPic(String url, WebDriver driver, String buttonId) throws InterruptedException, AWTException {
        String path = "F:\\temp\\" + url.substring(url.lastIndexOf('/') + 1);
        downloadPicture(url, path);
        File file = new File(path);
        if (file.exists()) {
            StringSelection sel = new StringSelection(path);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel, null);

            //定位上传按钮， 添加本地文件
            driver.findElement(By.id(buttonId)).click();
            Thread.sleep(1000);
            // 新建一个Robot类的对象
            Robot robot = new Robot();
            Thread.sleep(1000);
            // 按下回车
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(20);
            robot.keyRelease(KeyEvent.VK_ENTER);

            // 按下 CTRL+V
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.delay(20);

            // 释放 CTRL+V
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
            robot.delay(50);

            // 点击回车 Enter
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(20);
            robot.keyRelease(KeyEvent.VK_ENTER);
            WebDriverWait wait = new WebDriverWait(driver, 10, 1);
            wait.until((ExpectedCondition<WebElement>) text -> text.findElement(By.className("layui-layer-btn0"))).click();
        }
    }


    private WebDriver init(int type) {
        WebDriver driver = null;
        switch (type) {
            case 1:
                // chromedriver服务地址
                System.setProperty("webdriver.chrome.driver", "D:\\java\\chromedriver\\chromedriver.exe");
                ChromeOptions chromeOptions = new ChromeOptions();
//                chromeOptions.setHeadless(true);
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.managed_default_content_settings.images", 1);
                prefs.put("profile.content_settings.plugin_whitelist.adobe-flash-player", 1);
                prefs.put("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player", 1);
                chromeOptions.setExperimentalOption("prefs", prefs);

                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                // 新建一个WebDriver 的对象，但是new 的是谷歌的驱动
                driver = new ChromeDriver(chromeOptions);
                break;
            case 2:
                System.setProperty("webdriver.firefox.bin", "E://Program Files//Mozilla Firefox//firefox.exe");
                System.setProperty("webdriver.gecko.driver", "E://java//chromedriver_win32//geckodriver.exe");
                FirefoxOptions options = new FirefoxOptions();
                options.addPreference("plugin.state.flash", 2);
                driver = new FirefoxDriver(options);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        return driver;
    }

    //链接url下载图片
    private static void downloadPicture(String urlList, String path) {
        URL url = null;
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(String.format("document.getElementById('%s').style.display='block';", SaleInputFields.PROPERTY_YEAR_ID));
    }

}
