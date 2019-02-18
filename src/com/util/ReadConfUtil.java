package com.util;

import java.io.InputStream;
import java.util.ResourceBundle;

public class ReadConfUtil {
	public static void main(String[] args) {
		ResourceBundle resource = ResourceBundle.getBundle("conf");
		String key = resource.getString("oracleip"); 
		System.out.println(key);
	}
}
