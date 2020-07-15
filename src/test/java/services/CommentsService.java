package services;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static services.RequestPaths.COMMENTS;
import static tests.BaseTest.requestSpec;

public class CommentsService {

    public static Response getAllCommentsOfPosts(int postId) {
        return given().spec(requestSpec).param("postId", postId)
                .when().get(COMMENTS);
    }
}
