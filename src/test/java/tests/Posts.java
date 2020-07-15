package tests;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojos.Post;
import services.PostsService;
import services.UsersService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class Posts {

    private static final Logger LOGGER = LoggerFactory.getLogger(Users.class);
    private int userId;

    @Test
    public void verify_If_user_is_able_to_create_post() {
        //Arrange
        userId = UsersService.getUserIdOfUser("Delphine");

        //Act & Assert
        if (userId != 0) {
            PostsService.createPost("Title", "This is my new post", userId)
                    .then().assertThat()
                    .body("title", equalTo("Title"))
                    .body("body", equalTo("This is my new post"))
                    .body("userId", equalTo(userId));
        } else {
            Assert.fail("User does not exist, therefore unable to create post.");
        }
    }

    @Test
    public void verify_If_user_is_able_to_update_post() {
        //Arrange
        userId = UsersService.getUserIdOfUser("Delphine");

        //Act & Assert
        PostsService.updatePost("Updated Post", "Post updated", userId, 1)
                .then().assertThat()
                .body("title", equalTo("Updated Post"))
                .body("body", equalTo("Post updated"));
    }

    @Test
    public void verify_If_user_is_able_to_delete_post() {
        //Arrange
        userId = UsersService.getUserIdOfUser("Delphine");

        //Act & Assert
        PostsService.deletePost(1)
                .then().assertThat()
                .body(equalTo("{}"));
    }

    @Test
    public void verify_If_user_is_able_to_list_posts_of_a_specific_user() {
        //Arrange
        userId = UsersService.getUserIdOfUser("Delphine");

        //Act & Assert
        List<Post> allPostsByUser = Arrays.asList(PostsService.getPostsOfUser(userId).getBody().as(Post[].class));
        allPostsByUser.forEach(post -> Assert.assertEquals(post.getUserId(), userId));

    }

    @Test
    public void verify_If_no_posts_are_retrieved_for_an_invalid_user() {

        List<Post> allPostsByUser = Arrays.asList(PostsService.getPostsOfUser(123).getBody().as(Post[].class));
        Assert.assertEquals("Posts exists for this user.", 0, allPostsByUser.size());
    }
}
