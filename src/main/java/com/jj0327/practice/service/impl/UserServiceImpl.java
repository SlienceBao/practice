package com.jj0327.practice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jj0327.practice.entity.User;
import com.jj0327.practice.mapper.UserMapper;
import com.jj0327.practice.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jj0327.practice.util.SmsChengLiYe;
import com.jj0327.practice.util.SmsEmay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jin
 * @since 2018-09-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public int senfmsg(String content) {
        // 1. 查询需要发送短信的用户
        User user = new User();
//        List<User> users = user.selectList(new QueryWrapper());
        // 2. 发送短信
//        for (User tempUser :users) {
        return 1;
//        }
    }

    public static void main(String[] args) {
//		System.out.println(new SendMessageService().sendRegMessage("15858189371", "房探测试"));

		String userName = "clyftw168";
        String password = "0k8kumk6";
        String content = "【房探007】您的验证码为：123456";
        String mobile = "17630121024";
        String Info = null;
        long code = 0;
        try {
            SmsChengLiYe client = new SmsChengLiYe(userName, password);
            String result = null;// client.sendSMS(URLEncoder.encode(content, "UTF-8"), mobile, "", "", "", "");
//            SmsChengLiYe instance = SmsChengLiYe.getInstance();
            result = client.sendSMS(content, mobile, "", "", "", "");
            System.out.println(result);
            String strCode = result.split("\n")[0];
            code = Long.valueOf(strCode);
            if (code > 0) {//成功提交
                Info = "发送成功";
            } else if (code == 0) {
                Info = "发送失败";
            } else if (code == -1) { // 用户名密码不正确
                Info = "用户名密码不正确";
            } else if (code == -2) { // 必填选项为空
                Info = "必填选项为空";
            } else if (code == -3) { // 短信内容0个字节
                Info = "短信内容0个字节";
            } else if (code == -4) { // 0个有效号码
                Info = "0个有效号码";
            } else if (code == -5) { // 余额不够
                Info = "余额不够";
            } else if (code == -10) { // 用户被禁用
                Info = "用户被禁用";
            } else if (code == -11) { // 短信内容过长
                Info = "短信内容过长";
            } else if(code == -12){	 //用户无扩展权限
                Info = "无扩展权限";
            } else if(code == -13){  //IP地址校验错
                Info = "IP校验错误";
            } else if(code == -14){  //内容解析异常
                Info = "内容解析异常";
            } else {
                Info = "未知错误";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("返回信息：" + Info + "--" + code + "--");// + client.getPwd()


    }
}
