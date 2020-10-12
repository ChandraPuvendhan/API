package com.qa.tests;


import static io.restassured.RestAssured.given;


import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import org.testng.annotations.Test;

import com.qa.base.TestBase;

import io.restassured.response.Response;



public class APITest extends TestBase{

	Map<String, String> data=new HashMap<String, String>();
	JSONObject jsonObject;
	Response response;
	String responseBody;
	@Test(priority = 1)
	public void AddPlaceInGoogleMap() {

		response=given()
				.baseUri(base_Url)
				.basePath(post_url)
				.queryParam("key", keyValue)
				.body(file)
				.when()
				.post();

		response
		.then().assertThat().statusCode(RESPONSE_STATUS_CODE_200);
		responseBody=response.then().extract().body().asString();

		placeId=response.jsonPath().get("place_id");
		System.out.println("place_id : "+placeId);
		System.out.println("Add Response Body : " +responseBody);
	}
	@Test(priority = 2)	
	public void DisplayPlaceInGoogleMap() {
		System.out.println("place_id : "+placeId);
		response=given()
				.baseUri(base_Url)
				.basePath(get_url)
				.queryParam("place_id", placeId )
				.queryParam("key", keyValue)
				.get();

		response
		.then().assertThat().statusCode(RESPONSE_STATUS_CODE_200);
	
		responseBody=response.then().extract().body().asString();

		System.out.println("Get Response Body : " +responseBody);
	}
	@Test(priority = 3)	
	public void UpdatePlaceInGoogleMap() {
		
		System.out.println("place_id : "+placeId);
		
		data.put("place_id", placeId);
		data.put("address", "70 winter walk, USA");
		data.put("key", "qaclick123");
		data.put("body", "this is REST Assured Tutorial");
		jsonObject = new JSONObject(data);
		response=given()
				.baseUri(base_Url)
				.basePath(update_url)
				.queryParam("key", keyValue)
				.body(jsonObject)
				.when()
				.put();

		response
		.then().assertThat().statusCode(RESPONSE_STATUS_CODE_200);
		responseBody=response.then().extract().body().asString();

		System.out.println("Update Response Body : " +responseBody);
		DisplayPlaceInGoogleMap();
	}

	@Test(priority = 4)	
	public void DeletePlaceInGoogleMap() {
		
		System.out.println("place_id : "+placeId);
		data.put("key", "qaclick123");
		response=given()
				.baseUri(base_Url)
				.basePath(delete_url)
				.queryParam("key", keyValue)
				.body(jsonObject)
				.when()
				.delete();

		response
		.then().assertThat().statusCode(RESPONSE_STATUS_CODE_200);
		responseBody=response.then().extract().body().asString();

		System.out.println("Delete Response Body : " +responseBody);
		
	}

}
