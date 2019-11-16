package pageobject.test;

import org.jsoup.helper.HttpConnection;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import model.User.User;

public class WebServiceTest {
	@BeforeTest
	public void  initTest(){
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
	}

	@Test
	public void checkStatus(){
		Response response = RestAssured.when().get("/users").andReturn();
		Assert.assertEquals(response.getStatusCode(),200);
	}

	@Test
	public void checkHeader(){
		Response response = RestAssured.when().get("/users").andReturn();
		String headerType = response.getHeader("Content-Type");
		Assert.assertEquals(headerType, "application/json; charset=utf-8");
	}

	@Test
	public void checkBody(){
		Response response = RestAssured.when()
				.get("/users").andReturn();
		ResponseBody<?> responseBody = response.getBody();
		User[] users = responseBody.as(User[].class);
		Assert.assertEquals(users.length, 10);
	}

}
