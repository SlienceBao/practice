package com.jj0327.practice.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import com.jj0327.practice.entity.base.Result;
import com.jj0327.practice.service.SeleniumService;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.HttpCookie;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
            System.setProperty("webdriver.firefox.bin", "E://Program Files//Mozilla Firefox//firefox.exe");
            System.setProperty("webdriver.gecko.driver", "E://java//chromedriver_win32//geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless"); //无浏览器模式
            driver = new FirefoxDriver(options);//实例化
//        driver.manage().window().maximize();
            //全局隐式等待，等待
//        driver.manage().addCookie(new Cookie("sfut", "68D573B980904D90F63997C47E65CE1B9CC96405A88043421142195A05E824E55AA72F10CA4DF2E52CCE2B7F9405D5DBAB486265F0A49CD137E5E340C96F76DA2D577C33873E5C71F8B4A5B9E9C3ABB30AE527EAAB2F24B9"));
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            //设定网址
            driver.get("https://2.fang.com/Default.aspx");

            WebElement loginButton = driver.findElement(By.id("loginID"));
            loginButton.click();
            WebElement loginButton2 = driver.findElement(By.cssSelector("[class='login-tab fr']"));
            loginButton2.click();

            WebElement text = driver.findElement(By.id("txtusername"));
            text.sendKeys(username);
            Thread.sleep(1000);
            WebElement text2 = driver.findElement(By.id("password"));
            text2.sendKeys(password);
            Thread.sleep(1000);
            text2.submit();

            WebElement loginButton3 = driver.findElement(By.id("imgbt_login"));
            loginButton3.click();
            //显示等待控制对象
            WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
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
                driver.close();
            }
        }
    }

    @Override
    public Result publish(String username, String password) {
        String s = stringRedisTemplate.opsForValue().get("soufang:cookie:" + username);
        if (StringUtils.isBlank(s)) {
            System.out.println("=======================重新登录=========================");
            Result<String> login = login(username, password);
            if (login.getCode() != Result.SUCCESS_CODE) {
                return login;
            }
            String data = login.getData();
        }
        Set objects = JSONObject.parseObject(s, Set.class);

        WebDriver driver = null;
        try {
            System.setProperty("webdriver.firefox.bin", "E://Program Files//Mozilla Firefox//firefox.exe");
            System.setProperty("webdriver.gecko.driver", "E://java//chromedriver_win32//geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();
//            options.addPreference("profile.manged_default_content_settings.image", 1);
//            options.addPreference("profile.content_settings.plugin_whitelist.adobe-flash-player", 1);
//            options.addPreference("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player", 1);
//            options.addArguments("--headless"); //无浏览器模式
//            options.setHeadless(true);
            driver = new FirefoxDriver(options);//实例化
            //设定网址
            driver.get("https://2.fang.com/Default.aspx");
            driver.manage().addCookie(new Cookie("sfut", "D1D4D69B572CCFCFA3BD6457A691A073E15438EA9AC4D19BCCC4B4EDEF043E1E6B60236CE733F12A66883D9E632FE58A01DBEC70F16C12E4CA5C2DE97C8CE33B36B19D19DB57B4AD8D6EEF63ACBCB7EBA8FAAD82B27316C7"));
            Thread.sleep(2000);

            driver.get("https://2.fang.com/magent/house/sale/saleinput.aspx");

            WebDriverWait webDriverWait = new WebDriverWait(driver, 5);

            driver.findElement(By.className("layui-layer-btn0")).click();


            WebElement text = driver.findElement(By.id("input_PROJNAME"));
            text.sendKeys("凤凰城");
            Thread.sleep(1000);
            text.sendKeys(Keys.ENTER);
            // 产权
            ((JavascriptExecutor) driver).executeScript("document.getElementById('options_input_PropertyYear').style.display='block';");
            // TODO 选择产权年限
//           ((JavascriptExecutor) driver).executeScript("setSelectItem(4, 1, 'input_PropertyYear')");
            driver.findElement(By.linkText("50年")).click();

            ((JavascriptExecutor) driver).executeScript("document.getElementById('options_input_y_str_PAYINFO0').style.display='block';");
            driver.findElement(By.linkText("公司产权")).click();
            Thread.sleep(1000);
//            driver.findElement(By.id("select_input_PropertySubType")).click();
            ((JavascriptExecutor) driver).executeScript("document.getElementById('options_input_PropertySubType').style.display='block';");
            driver.findElement(By.linkText("公寓")).click();
            Thread.sleep(1000);

            driver.findElement(By.id("input_ROOM")).sendKeys("1");
            Thread.sleep(1000);
            WebElement input_hall = driver.findElement(By.id("input_HALL"));
            input_hall.clear();
            input_hall.sendKeys("2");
            Thread.sleep(1000);
            WebElement input_toilet = driver.findElement(By.id("input_TOILET"));
            input_toilet.clear();
            input_toilet.sendKeys("3");
            Thread.sleep(1000);
            WebElement input_kitchen = driver.findElement(By.id("input_KITCHEN"));
            input_kitchen.clear();
            input_kitchen.sendKeys("4");
            Thread.sleep(1000);
            WebElement input_balcony = driver.findElement(By.id("input_BALCONY"));
            input_balcony.clear();
            input_balcony.sendKeys("5");
            Thread.sleep(1000);
            // 结构: 错层,跃层,复式,开间,平层
            ((JavascriptExecutor) driver).executeScript("document.getElementById('options_input_HouseStructure').style.display='block';");
            driver.findElement(By.linkText("开间")).click();
            Thread.sleep(1000);
            // 装修: 豪华装修,精装修, 中装修, 简装修, 毛坯
            ((JavascriptExecutor) driver).executeScript("document.getElementById('options_input_HouseFITMENT').style.display='block';");
            driver.findElement(By.linkText("精装修")).click();
            Thread.sleep(1000);
            // 朝向: 东,南,西,北,东南,西南,西北,东北,南北,东西
            ((JavascriptExecutor) driver).executeScript("document.getElementById('options_input_HouseFORWARD').style.display='block';");
            driver.findElement(By.linkText("南北")).click();
            Thread.sleep(1000);
            // 类别: 板楼, 塔楼, 砖房, 砖混, 平房, 钢混, 塔板结合
            ((JavascriptExecutor) driver).executeScript("document.getElementById('options_input_BuildingType').style.display='block';");
            driver.findElement(By.linkText("塔楼")).click();
            Thread.sleep(1000);
            // 建筑面积
            driver.findElement(By.id("BuildingArea")).sendKeys("89.92");
            Thread.sleep(1000);
            // 使用面积
            driver.findElement(By.id("input_LIVEAREA")).sendKeys("79.73");
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
            // 配套设置: 暖气
            driver.findElement(By.id("houseTitle")).sendKeys("我是标题");
            Thread.sleep(1000);
            // 标题
            driver.findElement(By.id("houseTitle")).sendKeys("我是标题");
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

            //定位上传按钮， 添加本地文件
            WebElement sfile_1 = driver.findElement(By.id("Sfile_1"));
            sfile_1.click();

            Thread.sleep(5000);





            //显示等待控制对象
//        String page = driver.getPageSource();//获取网页全部信息
            Set<Cookie> cookies = driver.manage().getCookies();
            return Result.success("OK", s);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(-1, e.getMessage());
        } finally {
            if (driver != null) {
//                driver.quit();
            }
        }
    }

    public static void main(String[] args) {
        HttpResponse execute = HttpRequest.get("https://probe.3g.fang.com/Prick.do?aip=1&aa=1&at=1&fst=3&fet=0&fn=fileclickmap&dn=clickmap&cst=`&data=agentmainput`2.fang.com`https%3A//2.fang.com/magent/house/sale/saleinput.aspx%23`agentmainput_salesentry_00``1366`380`4213`nyd9utnx1g4ucca60e82yt3e61yjx5wmzcr`U_booe07obok5ppuybve7vrw2dv12jxwks4is`i`hzagentmainput&random=0.3598727674204494").cookie(new HttpCookie("sfut", "D1D4D69B572CCFCFA3BD6457A691A073E15438EA9AC4D19BCCC4B4EDEF043E1E6B60236CE733F12A66883D9E632FE58A01DBEC70F16C12E4CA5C2DE97C8CE33B36B19D19DB57B4AD8D6EEF63ACBCB7EBA8FAAD82B27316C7")).execute();
        System.out.println(execute.body());
    }
}
