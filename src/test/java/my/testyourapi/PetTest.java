package my.testyourapi;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class PetTest extends BaseTest {

    @Test
    public void getPetByID(){

        given().spec(requestSpec)
        .when().get(RequestPaths.PET + "1")
        .then().statusCode(SC_OK);
    }
}
