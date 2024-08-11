package tests;

import com.codeborne.selenide.Condition;
import io.restassured.response.Response;
import models.AuthModel;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
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

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.path("userId")));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.path("expires")));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.path("token")));

        open("/profile");
        $("#userName-value").should(Condition.text(getUserName()));
    }
}
