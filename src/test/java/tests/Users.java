package tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;
import services.UsersService;


import static org.hamcrest.Matchers.greaterThan;


public class Users {
    private static final Logger LOGGER = LoggerFactory.getLogger(Users.class);

    @Test
    public void verify_If_all_users_are_listed() {
        UsersService.getAllUsers()
                .then().assertThat().body("size()", greaterThan(0));

    }

    @Test
    public void verify_If_specific_user_is_listed() {

        //If user does not exist then return will be zero.
        Assert.assertNotEquals(0, UsersService.getUserIdOfUser("Delphine"));
    }

    @Test
    public void verify_If_no_information_is_returned_for_an_invalid_user() {
        //If user does not exist then return will be zero.
        Assert.assertEquals(0, UsersService.getUserIdOfUser("I do not exist."));
    }
}
