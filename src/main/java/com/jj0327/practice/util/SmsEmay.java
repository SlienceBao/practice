package com.jj0327.practice.util;


import cn.emay.sdk.api.Mo;
import cn.emay.sdk.api.SDKClient;
import cn.emay.sdk.api.SDKService;

import java.util.ArrayList;
import java.util.List;

/***
 * 亿美软通短信
 * @author pj
 *
 */
public class SmsEmay {
	
//	static ResourceBundle bundle=PropertyResourceBundle.getBundle("config");
	SDKService service = new SDKService();
	private static SDKClient sDKClientSOAImpl= null;
	private static SmsEmay client=null;
	static String softwareSerialNo="3SDK-EMY-0130-NFSQP";
	static String key="PEPqBGyhKw";

	public SmsEmay(String softwareSerialNo, String key) {
		this.softwareSerialNo = softwareSerialNo;
		this.key = key;
		sDKClientSOAImpl=service.getSDKService();
	}
	public synchronized static SmsEmay getInstance(String softwareSerialNo, String key){
		if(client==null){
			client=new SmsEmay(softwareSerialNo,key);
		}
		return client;
		
	}
	
	public synchronized static SmsEmay getInstance(){
		
		if(client==null){
			client=new SmsEmay(softwareSerialNo,key);
		}
		return client;
		
	}

	public int chargeUp(String cardNo, String cardPass) {
		return sDKClientSOAImpl.chargeUp(softwareSerialNo, key, cardNo,
				cardPass);
	}

	public double getBalance() {
		return sDKClientSOAImpl.getBalance(softwareSerialNo, key);
	}

	public double getEachFee() {
		return sDKClientSOAImpl.getEachFee(softwareSerialNo, key);
	}

	public List<Mo> getMO() {
		return sDKClientSOAImpl.getMO(softwareSerialNo, key);
	}

	public String getVersion() {
		return sDKClientSOAImpl.getVersion();
	}

	public int logout() {
		return sDKClientSOAImpl.logout(softwareSerialNo, key);
	}

	public int registDetailInfo(String eName, String linkMan, String phoneNum,
			String mobile, String email, String fax, String address,
			String postcode) {
		return sDKClientSOAImpl.registDetailInfo(softwareSerialNo, key, eName,
				linkMan, phoneNum, mobile, email, fax, address, postcode);
	}

	public int registEx(String serialpass) {
		System.out.println(softwareSerialNo + " - " + key);
		return sDKClientSOAImpl.registEx(softwareSerialNo, key, serialpass);
	}

	/***
	 * 短信发送
	 * @param sendTime 发送时间
	 * @param mobiles 手机号码
	 * @param smsContent 短信内容
	 * @param addSerial 附加码
	 * @param srcCharset 字符编码，默认为"GBK"
	 * @param smsPriority 短信等级，范围1~5，数值越高优先级越高
	 * @return
	 */
	public int sendSMS(String sendTime, String[] mobiles, String smsContent, String addSerial, String srcCharset, int smsPriority) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < mobiles.length; i++) {
			list.add(mobiles[i]);
		}
		return sDKClientSOAImpl.sendSMS(softwareSerialNo, key, sendTime, list, smsContent, addSerial, srcCharset, smsPriority);
	}

	public int serialPwdUpd(String serialPwd, String serialPwdNew) {
		return sDKClientSOAImpl.serialPwdUpd(softwareSerialNo, key, serialPwd,
				serialPwdNew);
	}

	public int setMOForward(String forwardMobile) {
		return sDKClientSOAImpl.setMOForward(softwareSerialNo, key,
				forwardMobile);
	}

	public int setMOForwardEx(String[] forwardMobile) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < forwardMobile.length; i++) {
			list.add(forwardMobile[i]);
		}
		return sDKClientSOAImpl.setMOForwardEx(softwareSerialNo, key, list);
	}
	
	public int cancelMOForward() {
		return sDKClientSOAImpl.cancelMOForward(softwareSerialNo, key);
	}

}
