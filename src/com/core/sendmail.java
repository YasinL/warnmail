package com.core;

import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.sun.mail.handlers.message_rfc822;
import com.util.SmtpUtil;

public class sendmail {
	static ResourceBundle resource = ResourceBundle.getBundle("conf");
	static String myEmailSMTPHost = resource.getString("myEmailSMTPHost"); 
	static String myEmailAccount = resource.getString("myEmailAccount"); 
	static String myEmailPassword = resource.getString("myEmailPassword"); 
	static String smtpport = resource.getString("smtpport"); 
	static String receiveMailAccount = resource.getString("receiveMailAccount"); 
	
	public static  void send() throws Exception{
		String [] mail = receiveMailAccount.split(",");
		for (int i = 0; i < mail.length; i++) {
		    Properties props = new Properties();                    // 参数配置
		    final String smtpPort = smtpport;
		    props.setProperty("mail.smtp.port", smtpPort);
		    props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
		    props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
		    props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

		    // 2. 根据配置创建会话对象, 用于和邮件服务器交互
		    Session session = Session.getDefaultInstance(props);
		    session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log
		    // 3. 创建一封邮件
		    SmtpUtil sendmail =  new SmtpUtil();
		    MimeMessage message = sendmail.createMimeMessage(session, myEmailAccount, mail[i],"推荐有礼相关定时任务监控告警","推荐有礼相关定时任务监控告警,表sdecp.t_login_log_yestertoday无数据");

		    // 4. 根据 Session 获取邮件传输对象
		    Transport transport = session.getTransport();

		    // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
		    // 
		    //    PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
		    //           仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
		    //           类型到对应邮件服务器的帮助网站上查看具体失败原因。
		    //
		    //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
		    //           (1) 邮箱没有开启 SMTP 服务;
		    //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
		    //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
		    //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
		    //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
		    //
		    //    PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
		    transport.connect(myEmailAccount, myEmailPassword);

		    // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
		    transport.sendMessage(message, message.getAllRecipients());

		    // 7. 关闭连接
		    System.out.println("邮件发送成功");
		    transport.close();
		}
}
	public static void main(String[] args) throws Exception {
		sendmail test = new sendmail();
		sendmail.send();
////String [] mail = receiveMailAccount.split(",");
////for (int i = 0; i < mail.length; i++) {
////	System.out.println(mail[i]);
////}
		
	}
}

