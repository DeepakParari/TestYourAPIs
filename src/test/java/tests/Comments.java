package tests;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojos.Comment;
import pojos.Post;
import services.CommentsService;
import services.PostsService;
import services.UsersService;

import java.util.Arrays;
import java.util.List;

public class Comments {

    private static final Logger LOGGER = LoggerFactory.getLogger(Users.class);
    private int userId;
    private Response allCommentsOnUserPostsResponse;
    private List<Comment> allCommentsOnUserPosts;

    @Test
    public void verify_If_comments_of_an_user_have_valid_email_format() {

        //Arrange
        userId = UsersService.getUserIdOfUser("Delphine");
        List<Post> allPostsByUser = Arrays.asList(PostsService.getPostsOfUser(userId).getBody().as(Post[].class));
        allPostsByUser.forEach(post -> {
            int postId = post.getId();
            //Act
            allCommentsOnUserPostsResponse = CommentsService.getAllCommentsOfPosts(postId);
            allCommentsOnUserPosts = Arrays.asList(allCommentsOnUserPostsResponse.getBody().as(Comment[].class));
            String emailRegex = "^(.+)@(.+)$";
            allCommentsOnUserPosts.forEach(comment -> {
                //Assert
                Assert.assertTrue(comment.getEmail().matches(emailRegex));
            });
        });
    }

    @Test
    public void verify_If_no_comments_are_retrieved_from_an_invalid_post() {
        List<Comment> comments = Arrays.asList(CommentsService.getAllCommentsOfPosts(123).getBody().as(Comment[].class));
        Assert.assertEquals("Comments found for this post.", 0, comments.size());
    }
}
