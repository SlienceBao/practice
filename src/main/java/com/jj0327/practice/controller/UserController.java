package com.jj0327.practice.controller;


import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jj0327.practice.annotation.WebLogAnnotation;
import com.jj0327.practice.entity.User;
import com.jj0327.practice.entity.base.Result;
import com.jj0327.practice.service.SeleniumService;
import com.jj0327.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jin
 * @since 2018-09-28
 */
@RestController
@RequestMapping("/user")
public class UserController<main> {

    @Autowired
    UserService userService;
    @Resource
    SeleniumService seleniumService;


    @GetMapping("/page")
    @WebLogAnnotation(description = "获取分页")
    public IPage<User> getList(Page userPage, User user) {
        IPage<User> userIPage = user.selectPage(new Page<User>(userPage.getCurrent(), userPage.getSize()),
                new QueryWrapper<User>().select("id", "nickname").eq("id", 1 / 0));
        return userIPage;
    }

    @GetMapping("/sendmsg")
    @WebLogAnnotation(description = "发送短信")
    public int sendmsg() {
        String content = "测试短信内容";
        return userService.senfmsg(content);
    }

    @GetMapping("/login")
    public Result login(String username, String pwd) {
        return seleniumService.login("tongyuelong1313", "tyl199604");
    }

    @GetMapping("/publish")
    Result publish(String username, String pwd) {
        return seleniumService.publish("tongyuelong1313", "tyl199604");
    }

    public static void main(String[] args) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("cateId", 14);
        params.put("broker_id", 1367708);
        params.put("bianHao", null);
        params.put("params_435", null);
        params.put("params_438", null);
        params.put("params_448", null);
        params.put("params_436", null);
        params.put("params_113", 1);
        params.put("params_112", 1);
        params.put("params_114", 1);
        params.put("params_122", 1);
        params.put("params_10", 1297);
        params.put("params_374", 2987);
        params.put("params_107", "华星世纪大楼");
        params.put("xiaoquId", 102232984);
        params.put("xiangXiDiZhi", "体育场路");
        params.put("isSelectedFromList", 1);
        params.put("params_105", 120.159715);
        params.put("params_106", 30.275353);
        params.put("params_123", 17);

        HttpResponse execute = HttpRequest.post("http://vip.anjuke.com/house/publish/office/?jpChooseType=1")
                .cookie("sessid=7B3447F0-FB86-9ED1-4C60-B2F5BD25A303; aQQ_brokerguid=E27F39F1-8A63-2458-8504-D40D9696D4DD; wmda_uuid=e76280da9d148827944451398bba8958; wmda_new_uuid=1; wmda_visited_projects=%3B8920741036080; lps=http%3A%2F%2Flogin.anjuke.com%2Flogin%2Fverify%3Ftoken%3Dc017cd4de1891e21192275e225ba971f%26source%3D1%7Chttp%3A%2F%2Fvip.anjuke.com%2Flogin; ctid=18; twe=2; 58tj_uuid=bf1e678c-79b2-4813-8d0f-791efe6fef0d; als=0; wmda_session_id_8920741036080=1562824354306-02def9d8-941a-7dca; _ga=GA1.2.280587725.1562824360; _gid=GA1.2.730815822.1562824360; anjukereg=VNas2AhvSvhQQzgo; aQQ_ajkguid=F9594B85-C976-EA72-C3C8-3C952E5B35F4; ajk_member_id=8690757; ajk_member_name=1394330040rma; ajk_member_key=b0f502ffdf984d6c6891cfabe034099b; ajk_member_time=1594366920; aQQ_ajkauthinfos=Vtusil44G%2FhdQDEpAfLbjFMEINMb6VFSfGKecV1p6NSiHInGTqs2VzWkVpS%2B%2FsCaLHknSEWBZIW2PaWKkMU%2BPBXL; lui=8690757%3A2; new_session=1; init_refer=http%253A%252F%252Fvip.anjuke.com%252Flogin%252F; new_uv=4; ajk_broker_id=1367708; ajk_broker_ctid=18; ajk_broker_uid=8690757; aQQ_brokerauthinfos=bYbad3Bsb9KH%2FTIy%2FCkI8DZLaf%2Fs6Xj42HscErOCFlLAzov0Nb8CRwROQRq2i2U%2BrHLtEHs4v4qSzFzC6fTJ8Ip2QMRpVMIc1Y6cGH0L4YzUn5PnGWqtnbtpTL9iMpnWYOyMlYQ").form(params).execute();
        System.out.println("123: " + execute.body());
    }
}

