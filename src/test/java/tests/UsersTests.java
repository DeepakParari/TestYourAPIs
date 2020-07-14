package tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;
import pojos.User;
import services.UsersService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;



public class UsersTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersTests.class);

    @Test
    public void verify_If_Users_Exists(){
        UsersService.getAllUsers()
                .then().assertThat().body("size()", greaterThan(0));;
    }

    @Test
    public void verify_If_Specific_User_Exist(){
        List<User> users = Arrays.asList(UsersService.getSpecificUser("Delphine").getBody().as(User[].class));
        if (users.size() > 0) {
            Integer userId = users.get(0).getId();
        } else
            Assert.fail("No user found with username Delphine");
    }
}
