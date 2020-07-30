package tests;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojos.Post;
import services.PostsService;
import services.UsersService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

@RunWith(DataProviderRunner.class)
public class Posts extends BaseTest{

    private static final Logger LOGGER = LoggerFactory.getLogger(Users.class);
    private int userId;

    @DataProvider
    public static Object[][] createPost() {
        return new Object[][]{
                {"Delphine", "Title", "This is my new post"}
        };
    }

    @Test
    @UseDataProvider("createPost")
    public void verify_If_user_is_able_to_create_post(final String userName, final String title, final String body) {
        //Arrange
        userId = UsersService.getUserIdOfUser(userName);

        //Act & Assert
        if (userId != 0) {
            PostsService.createPost(title, body, userId)
                    .then().assertThat()
                    .body("title", equalTo(title))
                    .body("body", equalTo(body))
                    .body("userId", equalTo(userId));
        } else {
            Assert.fail("User does not exist, therefore unable to create post.");
        }
    }

    @DataProvider
    public static Object[][] updatePost() {
        return new Object[][]{
                {"Delphine", "Updated Post", "Post updated"}
        };
    }

    @Test
    @UseDataProvider("updatePost")
    public void verify_If_user_is_able_to_update_post(final String userName, final String title, final String body) {
        //Arrange
        userId = UsersService.getUserIdOfUser(userName);

        //Act & Assert
        PostsService.updatePost(title, body, userId, 1)
                .then().assertThat()
                .body("title", equalTo(title))
                .body("body", equalTo(body));
    }

    @DataProvider
    public static Object[][] user() {
        return new Object[][]{
                {"Delphine"}
        };
    }

    @Test
    @UseDataProvider("user")
    public void verify_If_user_is_able_to_delete_post(final String userName) {
        //Arrange
        userId = UsersService.getUserIdOfUser(userName);

        //Act & Assert
        PostsService.deletePost(1)
                .then().assertThat()
                .body(equalTo("{}"));
    }

    @Test
    @UseDataProvider("user")
    public void verify_If_user_is_able_to_list_posts_of_a_specific_user(final String userName) {
        //Arrange
        userId = UsersService.getUserIdOfUser(userName);

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
