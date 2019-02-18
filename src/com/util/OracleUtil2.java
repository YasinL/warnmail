package com.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.io.InputStream;
import java.util.ResourceBundle;
import oracle.jdbc.driver.OracleDriver;
import com.util.CurrentTime;



public class OracleUtil2 {


	public static void main(String[] args) {
		ResourceBundle resource = ResourceBundle.getBundle("conf");
		String oracleurl = resource.getString("oracleurl"); 
		String oracleuser = resource.getString("oracleuser"); 
		String oraclepw = resource.getString("oraclepw"); 
		String cutime = new  CurrentTime().cutime();
	{
	    Connection con = null;// 创建一个数据库连接
	    PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
	    ResultSet result = null;// 创建一个结果集对象
	    try
	    {
            Driver driver = new OracleDriver();
            DriverManager.deregisterDriver(driver);
//	        Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
	        System.out.println(cutime + "开始尝试连接数据库！");
	        String url = oracleurl;// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
	        String user = oracleuser;// 用户名,系统默认的账户名
	        String password = oraclepw;// 你安装时选设置的密码
	        con = DriverManager.getConnection(url, user, password);// 获取连接
	        System.out.println(cutime + "连接成功！");
	        String sql = "select * from t_kq_count";// 预编译语句，“？”代表参数
	        pre = con.prepareStatement(sql);// 实例化预编译语句
//	        pre.setString(1, "刘显安");// 设置参数，前面的1表示参数的索引，而不是表中列名的索引
	        result = pre.executeQuery();// 执行查询，注意括号中不需要再加参数
	        if (result.next()) {
				System.out.println(cutime + "结果不为空，正常");
			}
	        else {
				System.out.println(cutime + "结果为空");
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
	            if (result != null)
	                result.close();
	            if (pre != null)
	                pre.close();
	            if (con != null)
	                con.close();
	            System.out.println(cutime + "数据库连接已关闭！");
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }
	}
}
}

