package com.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.test.testmain;

import java.io.InputStream;
import java.util.ResourceBundle;
import oracle.jdbc.driver.OracleDriver;
import com.util.CurrentTime;



public class OracleUtil {




	public static boolean  query(String  executesql) {
		ResourceBundle resource = ResourceBundle.getBundle("conf");
		String oracleurl = resource.getString("oracleurl"); 
		String oracleuser = resource.getString("oracleuser"); 
		String oraclepw = resource.getString("oraclepw"); 
		String cutime = new  CurrentTime().cutime();
	
	    Connection con = null;// 创建一个数据库连接
	    PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
	    ResultSet results = null;// 创建一个结果集对象
	    try
	    {
            Driver driver = new OracleDriver();
            DriverManager.deregisterDriver(driver);
//	        Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
	        System.out.println(cutime + " 开始尝试连接数据库！");
	        String url = oracleurl;// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
	        String user = oracleuser;// 用户名,系统默认的账户名
	        String password = oraclepw;// 你安装时选设置的密码
	        con = DriverManager.getConnection(url, user, password);// 获取连接
	        System.out.println(cutime + " 连接成功！");
	        String sql = executesql;// 预编译语句，“？”代表参数
	        pre = con.prepareStatement(sql);// 实例化预编译语句
//	        pre.setString(1, "刘显安");// 设置参数，前面的1表示参数的索引，而不是表中列名的索引
	        results = pre.executeQuery();// 执行查询，注意括号中不需要再加参数
	        if ( results.next()) {
//				System.out.println("no null");
	        	return true;
			}
	        else {
	        	return false;
//	        	System.out.println("null");
			}

	        
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	    finally
	    {
	        try
	        {
	            // 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
	            // 注意关闭的顺序，最后使用的最先关闭
	            if (results != null)
	                results.close();
	            if (pre != null)
	                pre.close();
	            if (con != null)
	                con.close();
	            System.out.println(cutime + " 数据库连接已关闭！");
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }
		return false;
		
	}

	public static void main(String[] args) {
		OracleUtil test = new OracleUtil();
		boolean test1 = test.query("select * from  t_kq_count");
		System.out.println(test1);

		
	}
	
}


