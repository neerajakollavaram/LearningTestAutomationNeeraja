package restassuredtestcases;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import org.json.simple.JSONObject;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import io. restassured.filter.log.LogDetail;



public class Assignment8 {
	
	@Test
	public void testValidateSingleGetUserData() {
		baseURI = "https://reqres.in/api";
		given()
		.get("/users/2")
		.then()
		.statusCode(200)
		.body("data.first_name",equalTo("Janet"))
		.log().body()
		.log().all();
		
		
	}
	
	@Test
	public void testSingleUserNotFoundData() {
		
		baseURI = "https://reqres.in/api";
		given()
		.get("/users/23")
		.then()
		.statusCode(404)
		.log().ifStatusCodeIsEqualTo(404);
	}
	
	@Test
	public void ResourceListData() {
		baseURI = "https://reqres.in/api";
		given()
		.get("/unknown")
		.then()
		.statusCode(200)
		.body("data[0].name",equalTo("cerulean"))
		.body("data.pantone_value", hasItems("15-4020","17-2031"))
		.log().all();
	}
	
	@Test
	public void SingleResourceData() {
		baseURI = "https://reqres.in/api";
		given()
		.get("/unknown/2")
		.then()
		.statusCode(200)
		.body("data.name",equalTo("fuchsia rose"))
		.log().body()
		.log().all();		
	}
	
	@Test
	public void SingleResourceNotFoundData() {
		baseURI = "https://reqres.in/api";
		given()
		.get("/api/unknown/23")
		.then()
		.statusCode(404);		
	}
	@Test
	public void TestPostRegisterSucessful() {
	baseURI = "https://reqres.in/api";
		
		JSONObject reqData1 = new JSONObject();
		reqData1.put("email","eve.holt@reqres.in");
		reqData1.put("password","pistol");
		
		System.out.println(reqData1.toJSONString());
		given()
		.header("Connection","keep-alive")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
	     .body(reqData1.toJSONString())
	     .when()
	     	.post("/register")
	     .then()
	     .statusCode(200)
	     .log().all();	
	}
	@Test
	public void TestPostRegisterUnsuccessful() {
		
	baseURI = "https://reqres.in/api";
		
		JSONObject reqData2 = new JSONObject();
		reqData2.put("email", "Neeraja#123");
		System.out.println(reqData2.toJSONString());
		given()
		.header("Connection","keep-alive")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
	     .body(reqData2.toJSONString())
	     .when()
	     	.post("/register")
	     	.then()
	        .statusCode(400)
	        .log().all();	
	}
	@Test

	public void LoginSuccessful() {
		
	baseURI = "https://reqres.in/api";
		
		JSONObject reqData3 = new JSONObject();
		reqData3.put("email","eve.holt@reqres.in");
		reqData3.put("password","pistol");
		System.out.println(reqData3.toJSONString());
		given()
		.header("Content-Type", "application/json")
		.header("Connection","keep-alive")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(reqData3.toJSONString())
	    .when()
			.post("/login")
			.then()
	        .statusCode(200)
	        .log().all();		
	}
	@Test
	public void LoginUnSuccessful() {
	baseURI = "https://reqres.in/api";
		
		JSONObject reqData4 = new JSONObject();
		reqData4.put("email", "Neeraja$123");
		System.out.println(reqData4.toJSONString());
		given()
		.header("Content-Type", "application/json")
		.header("Connection","keep-alive")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(reqData4.toJSONString())
	    .when()
			.post("/login")
			.then()
	        .statusCode(400)
	        .log().all();		
		
	}
	

	@Test
	public void endToEndAPITest() {
	System.out.println("API TESTING");

	//Register user
	baseURI = "https://reqres.in/api";
	JSONObject reqData = new JSONObject();
	reqData.put("email","eve.holt@reqres.in");
	reqData.put("password","pistol");

	System.out.println(reqData.toJSONString());
	given()
	.header("Content-Type","application/json")
	.header("charset","utf-8")
	.header("Connection","keep-alive")
	.accept(ContentType.JSON)
	.body(reqData.toJSONString())
	.when()
	.post("/register")
	.then()
	.statusCode(200)
	.log().all();

	//extract token and print
	String token = given()
	.body(reqData.toJSONString())
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	.header("charset","utf-8")
	.when()
	.post("/register")
	.then()
	.extract().path("token");
	System.out.println("TOKEN : " + token);

	//extract userID and print
	int userID = given()
	.body(reqData.toJSONString())
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	.header("charset","utf-8")
	.when()
	.post("/register")
	.then()
	.extract().path("id");
	System.out.println("ID : " + userID);
	
	//User Login
	given()
	.header("Content-Type","application/json")
	.header("Connection","keep-alive")
	.accept(ContentType.JSON)
	.body(reqData.toJSONString())
	.when()
	.post("/login")
	.then()
	//verify status code
	.statusCode(200)
	.log().all();

	//Extract login token
	String Logintoken = given()
	.body(reqData.toJSONString())
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	.header("charset","utf-8")
	.when()
	.post("/login")
	.then()
	.extract().path("token");
	System.out.println("TOKEN : " + Logintoken);

	// get single user to find the same user id >>4
	given()
	.get("/users/" + userID)
	.then()
	.statusCode(200)
	.body("data.id", equalTo(4))
	.log().body();

	// SINGLE <RESOURCE> use the same user id >> validate details
	given()
	.get("/unknown/" +userID)
	.then()
	.statusCode(200)
	.body("data.id",equalTo(4) )
	.body("data.name", equalTo("aqua sky"))
	//.body("year",equalTo(2003))
	//we are logging everything
	.log().everything();//User Login
	given()
	.header("Content-Type","application/json")
	.header("Connection","keep-alive")
	.accept(ContentType.JSON)
	.body(reqData.toJSONString())
	.when()
	.post("/login")
	.then()
	//verify status code
	.statusCode(200)
	.log().all();

	//Extract login token
	String Log = given()
	.body(reqData.toJSONString())
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	.header("charset","utf-8")
	.when()
	.post("/login")
	.then()
	.extract().path("token");
	System.out.println("TOKEN : " + Log);

	// get single user to find the same user id >>4
	given()
	.get("/users/" + userID)
	.then()
	.statusCode(200)
	.body("data.id", equalTo(4))
	.log().body();

	// SINGLE <RESOURCE> use the same user id >> validate details
	given()
	.get("/unknown/" +userID)
	.then()
	.statusCode(200)
	.body("data.id",equalTo(4) )
	.body("data.name", equalTo("aqua sky"))
	//.body("year",equalTo(2003))
	//we are logging everything
	.log().everything();
	
	
	// patch same user >> validate response >> search user >> validate

	reqData.put("name","Neeraja");
	reqData.put("job","Student");
	given()
	.body(reqData.toJSONString())
	.when()
	.patch("/users/" + userID)
	.then()
	.statusCode(200)
	.log().body();

	// delete same user >> validate code >> search user >> validate
	when()
	.delete("/users/" + userID)
	.then()
	.statusCode(204)
	.log().ifStatusCodeIsEqualTo(204);

}
}	

