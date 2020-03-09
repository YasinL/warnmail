package com.core;

import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import com.util.HttpClientUtil;
import com.util.SmtpUtil;
import com.util.OracleUtil;
import com.util.SmtpUtil;
import com.core.sendmail;
import com.util.CurrentTime;

/*
 * 业务逻辑：查询表_*中是否有数据，表中没数据的话进行告警
 * @author liyunqing
 * */


public class mainmail {
	static ResourceBundle resource = ResourceBundle.getBundle("conf");
	static String receiveWarnPhone = resource.getString("receiveWarnPhone"); 
	static String smsurl = resource.getString("smsurl"); 

	public static void sendpone() {
		String [] phone = receiveWarnPhone.split(",");
		for (int i = 0; i < phone.length; i++) {
	        //发送 GET 请求
	        String s=HttpClientUtil.sendGet(smsurl, "phone="+ phone[i] + "&content=任务监控告警,表无数据");
	        System.out.println(s);
		}
	}
	public static void main(String[] args) throws Exception {
		OracleUtil orasql = new OracleUtil();
		String cutime = new  CurrentTime().cutime();
		boolean value = orasql.query("select * from  sdecp.t_login_log_yestertoday@to_app");
		System.out.println(value);
		if (value) {
			System.out.println(cutime + " 查询表中数据正常");
		}
		else {
			System.out.println(cutime + " 无数据");
			mainmail.sendpone();
			sendmail.send();
		}
		

	}

}

