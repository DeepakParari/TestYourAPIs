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
    public void verify_If_Users_Exists(){
        UsersService.getAllUsers()
                .then().assertThat().body("size()", greaterThan(0));;
    }

    @Test
    public void verify_If_Specific_User_Exist(){

        //If user does not exist then return will be zero.
        Assert.assertNotEquals(0,UsersService.getUserIdOfUser("Delphine"));
    }
}
