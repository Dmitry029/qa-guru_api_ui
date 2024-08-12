package authorization;

import io.restassured.response.Response;
import models.AuthModel;
import tests.BaseTest;

import static io.restassured.RestAssured.given;
import static specs.LoginUserSpec.loginRequestSpec;
import static specs.LoginUserSpec.loginResponseSpec;

public class AuthorizationApi extends BaseTest {
    public Response getAuthorizationResponse() {
        AuthModel model = new AuthModel(getUserName(), getPassword());

        return given(loginRequestSpec)
                .body(model)
                .when()
                .post("Account/v1/Login")
                .then()
                .spec(loginResponseSpec)
                .extract().response();
    }
}
