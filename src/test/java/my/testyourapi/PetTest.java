package my.testyourapi;

import helpers.JSONHelper;
import helpers.RequestLoader;
import io.restassured.response.Response;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static tests.BaseTest.requestSpec;
import static org.apache.http.HttpStatus.SC_OK;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PetTest {

    JSONHelper JSONHelper = new JSONHelper();
    public static String petID;

    @Ignore
    @Test
    public void getPetByID(){

        Response getPetByIDRes = given().spec(requestSpec)
                                .when().get(RequestPaths.PET + petID)
                                .then().statusCode(SC_OK).extract().response();
    }

    @Ignore
    @Test
    public void createPet(){
        Object createPetBody = new RequestLoader().loadJson("/payloads/createNewPet.json");

        Response createPetRes = given().spec(requestSpec).body(createPetBody)
                .when().post(RequestPaths.PET)
                .then().statusCode(SC_OK).extract().response();

        petID = JSONHelper.getValueFromJSON(createPetRes,"id");
    }

    @Ignore
    @Test
    public void updatePet(){
        Object updatePetBody = new RequestLoader().loadJson("/payloads/updatePet.json");
        updatePetBody = JSONHelper.setValueInJSON(updatePetBody,"id", Double.valueOf(petID));
        given().spec(requestSpec).body(updatePetBody)
                .when().put(RequestPaths.PET)
                .then().statusCode(SC_OK);
    }

    @Ignore
    @Test
    public void zdeletePet(){
        Response getPetByIDRes = given().spec(requestSpec)
                .when().delete(RequestPaths.PET + petID)
                .then().statusCode(SC_OK).extract().response();
    }
}
