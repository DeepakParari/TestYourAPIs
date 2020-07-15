package services;

import io.restassured.response.Response;
import org.junit.Assert;
import pojos.User;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static services.RequestPaths.USERS;
import static tests.BaseTest.requestSpec;

public class UsersService {

    public static Response getAllUsers() {

        return given().spec(requestSpec)
                .when().get(USERS);
    }

    private static Response getSpecificUser(String userName) {
        return given().spec(requestSpec).param("username", userName)
                .when().get(USERS);
    }

    public static int getUserIdOfUser(String userName) {
        int userId = 0;
        List<User> users = Arrays.asList(getSpecificUser(userName).getBody().as(User[].class));
        if (users.size() > 0) {
            userId = users.get(0).getId();
        }
        return userId;
    }
}
