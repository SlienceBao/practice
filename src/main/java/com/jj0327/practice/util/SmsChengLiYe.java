package com.jj0327.practice.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class SmsChengLiYe {

	/**
	 * http post方式提交
	 */
	private static String HttpURL = "http://www1.jc-chn.cn/";

	private String userName = null;//"clyftw168";
	private String password = null;//"0k8kumk6";
	private String pwd = "";
	private static SmsChengLiYe client=null;

//	static ResourceBundle bundle=PropertyResourceBundle.getBundle("config");
	
	public SmsChengLiYe(){};
	
	/**
	 * 构造函数
	 * @param username
	 * @param password
	 */
	public SmsChengLiYe(String username, String password) {
		this.userName = username;
		this.password = password;
		this.pwd = MD5Encode(username + MD5Encode(password));
	}

	public synchronized static SmsChengLiYe getInstance(){
		if(client==null){
//			client=new SmsChengLiYe(bundle.getString("ChengLiYekey"),bundle.getString("ChengLiYepwd"));
		}
		return client;
	}

	public synchronized static SmsChengLiYe getInstance(String username, String password){
		if(client == null){
			client = new SmsChengLiYe(username, password);
		}
		return client;
	}
	
	
	/**
	 * 方法名称：mt 
	 * 功 能：发送短信
	 * @param content 发送内容
	 * @param mobile  发送的手机号码，多个手机号为用半角 , 分开
	 * @param dstime  定时时间 ，为空时表示立即发送，格式：yyyy-MM-dd HH:mm:ss
	 * @param msgid   客户自定义消息ID
	 * @param ext	     用户自定义扩展
	 * @param msgfmt  提交消息编码格式（UTF-8/GBK）置空时默认是UTF-8
	 * 返 回 值：若用户自定义消息ID，则返回用户的ID，否则系统随机生成一个任务ID
	 * @throws UnsupportedEncodingException
	 */
 	public String sendSMS(String content, String mobile, String dstime,String msgid, String ext, String msgfmt) {
		String result = "";
		OutputStreamWriter out = null;
		BufferedReader in = null;
		StringBuilder params = new StringBuilder();
		params.append("username=").append(userName)
				.append("&password=").append(this.getPwd())
				.append("&mobile=").append(mobile)
				.append("&content=").append(content)
				.append("&dstime=").append(dstime)
				.append("&ext=").append(ext)
				.append("&msgid=").append(msgid)				
				.append("&msgfmt=").append(msgfmt);
		try {
			URL realUrl = new URL(HttpURL + "smsSend.do");
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF8");
			out.write(params.toString());
			out.flush();

			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF8"));
			String line = "";
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	/**
	 * 27 md5加密产生，产生128位（bit）的mac 28 将128bit Mac转换成16进制代码 29
	 * @param strSrc  30
	 * @param key   31
	 * @return 32
	 */
	public static String MD5Encode(String strSrc) {
		return MD5Encode(strSrc, "");
	}

	/**
	 * 27 md5加密产生，产生128位（bit）的mac 28 将128bit Mac转换成16进制代码 29
	 * @param strSrc 30
	 * @param key  31
	 * @return 32
	 */
	public static String MD5Encode(String strSrc, String key) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(strSrc.getBytes("UTF8"));
			StringBuilder result = new StringBuilder(32);
			byte[] temp;
			temp = md5.digest(key.getBytes("UTF8"));
			for (int i = 0; i < temp.length; i++) {
				result.append(Integer.toHexString((0x000000ff & temp[i]) | 0xffffff00).substring(6));
			}
			return result.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
}