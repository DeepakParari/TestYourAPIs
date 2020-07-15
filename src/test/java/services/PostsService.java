package services;

import io.restassured.response.Response;
import pojos.Post;

import static io.restassured.RestAssured.given;
import static tests.BaseTest.requestSpec;

public class PostsService {

  public static Response getPostsOfUser(int userId){
      return given().spec(requestSpec).param("userId", userId)
              .when().get("/posts");
  }

  public static Response createPost(String title, String body, int userId){

    //Create Post Body
    Post post = new Post();
    post.setTitle(title);
    post.setBody(body);
    post.setUserId(userId);

    return given().spec(requestSpec).body(post)
            .when().post("/posts");
  }

  public static Response updatePost(String title, String body, int userId, int postId){

    //Create Post Body
    Post post = new Post();
    post.setTitle(title);
    post.setBody(body);
    post.setUserId(userId);
    post.setId(postId);

    return given().spec(requestSpec).body(post)
            .when().put("/posts/" + postId);
  }

  public static Response deletePost(int postId){
    return given().spec(requestSpec)
            .when().delete("/posts/" + postId);
  }
}
