package my.testyourapi;

import helpers.RequestLoader;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PetTest extends BaseTest {


    @Test
    public void getPetByID(){

        given().spec(requestSpec)
        .when().get(RequestPaths.PET + "24")
        .then().statusCode(SC_OK);
    }

    @Test
    public void createPet(){
        Object createPetBody = new RequestLoader().loadJson("/payloads/createNewPet.json");

        given().spec(requestSpec).body(createPetBody)
                .when().post(RequestPaths.PET)
                .then().statusCode(SC_OK);
    }
}
