package tests;

import io.restassured.response.Response;
import models.AuthModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static specs.DeleteBookSpec.loginRequestSpec;
import static specs.DeleteBookSpec.loginResponseSpec;

public class DeleteBookTest extends BaseTest {

    @Test
    void deleteBookTest() {
        AuthModel model = new AuthModel(getUserName(), getPassword());
        Response authResponse = given(loginRequestSpec)
                .body(model)

                .when()
                .post("Account/v1/Login")

                .then()
                .spec(loginResponseSpec)
                .extract().response();
    }

}
