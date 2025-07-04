package tests;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class LoginAPITest {

	String baseURL = "https://reqres.in/api/login";

	@Test
	public void testPositiveLogin() {
		String requestBody = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

		Response response = given().header("Content-Type", "application/json").body(requestBody).post(baseURL);

		System.out.println("Positive Response: " + response.asString());
		System.out.println("Status Code: " + response.getStatusCode());

		assertEquals(response.getStatusCode(), 200);
		assertNotNull(response.jsonPath().getString("token"));
	}

	@Test
	public void testNegativeLogin() {
		String requestBody = "{ \"email\": \"peter@klaven\" }";

		Response response = given().header("Content-Type", "application/json").body(requestBody).post(baseURL);

		System.out.println("Negative Response: " + response.asString());
		System.out.println("Status Code: " + response.getStatusCode());

		assertEquals(response.getStatusCode(), 400);
		assertEquals(response.jsonPath().getString("error"), "Missing password");
	}
}
