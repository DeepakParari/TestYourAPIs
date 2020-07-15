package tests;

import helpers.JSONHelper;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojos.Post;
import pojos.User;
import services.PostsService;
import services.UsersService;

import static org.hamcrest.Matchers.equalTo;

public class Posts {

    private static final Logger LOGGER = LoggerFactory.getLogger(Users.class);
    private int userId;

    @Test
    public void verify_If_user_is_able_to_create_post(){
        //Arrange
        userId = UsersService.getUserIdOfUser("Delphine");

        //Act & Assert
        if(userId != 0) {
            PostsService.createPost("Title", "This is my new post", userId)
                    .then().assertThat()
                    .body("title", equalTo("Title"))
                    .body("body", equalTo("This is my new post"))
                    .body("userId", equalTo(userId));
        }
        else{ Assert.fail("User does not exist, therefore unable to create post."); }
    }

    @Test
    public void verify_If_user_is_able_to_update_post(){
        //Arrange
        userId = UsersService.getUserIdOfUser("Delphine");

        //Act & Assert
        PostsService.updatePost("Updated Post", "Post updated",userId,1)
                .then().assertThat()
                .body("title", equalTo("Updated Post"))
                .body("body", equalTo("Post updated"));
    }
}
