package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class TestBase {
	public int RESPONSE_STATUS_CODE_200 = 200;
	public int RESPONSE_STATUS_CODE_500 = 500;
	public int RESPONSE_STATUS_CODE_400 = 400;
	public int RESPONSE_STATUS_CODE_401 = 401;
	public int RESPONSE_STATUS_CODE_201 = 201;

	public static String placeId;
	public static String addfilePath="src/test/resources/AddPlace.json";
	public static String base_Url="https://rahulshettyacademy.com";
	public static String post_url="/maps/api/place/add/json";
	public static String get_url="/maps/api/place/get/json";
	public static String update_url="/maps/api/place/update/json";

	public static String delete_url="/maps/api/place/delete/json";
	
	public static String keyValue="qaclick123";
	public File file=new File(addfilePath);
	
	public Properties prop;
	public Logger logger;
	
	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	

}