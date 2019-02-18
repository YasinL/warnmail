package com.test;
import com.util.HttpClientUtil;
import com.util.OracleUtil;
public class testmain {
	public static void main(String[] args) {
        //发送 GET 请求
        String s=HttpClientUtil.sendGet("http://sendsms:8080/smspush/smssend/sending.do", "phone=15269136818&content=testtest");
        System.out.println(s);
//        
//        //发送 POST 请求
//        String sr=HttpClientUtil.sendPost("http://118.89.18.234/", "key=123&v=456");
//        System.out.println(sr);
//		 OracleUtil test = new OracleUtil();
//		 test.query("select * from t_kq_count");
//
//		 System.out.println(test);
    }
}
