package services;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static tests.BaseTest.requestSpec;

public class UsersService {

    public static Response getAllUsers() {

        return given().spec(requestSpec)
                .when().get("/users");
    }

    public static Response getSpecificUser(String userName) {
        return given().spec(requestSpec).param("username", userName)
                .when().get("/users");
    }
}
