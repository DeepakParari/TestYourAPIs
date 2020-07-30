package tests;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;
import services.UsersService;


import static org.hamcrest.Matchers.greaterThan;

@RunWith(DataProviderRunner.class)
public class Users extends BaseTest{
    private static final Logger LOGGER = LoggerFactory.getLogger(Users.class);

    @DataProvider
    public static Object[][] user() {
        return new Object[][]{
                {"Delphine"}
        };
    }

    @Test
    public void verify_If_all_users_are_listed() {
        UsersService.getAllUsers()
                .then().assertThat().body("size()", greaterThan(0));

    }

    @Test
    @UseDataProvider("user")
    public void verify_If_specific_user_is_listed(final String userName) {

        //If user does not exist then return will be zero.
        Assert.assertNotEquals(0, UsersService.getUserIdOfUser(userName));
    }

    @Test
    public void verify_If_no_information_is_returned_for_an_invalid_user() {
        //If user does not exist then return will be zero.
        Assert.assertEquals(0, UsersService.getUserIdOfUser("I do not exist."));
    }
}
