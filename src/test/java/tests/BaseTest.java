package tests;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import lombok.Data;
import org.junit.jupiter.api.BeforeAll;

@Data
public class BaseTest {
    private String userName = "TestUserName";
    private String password = "TestUserNameKdp110$";

    @BeforeAll
    static void setup(){
        Configuration.baseUrl = "https://demoqa.com";
        RestAssured.baseURI = "https://demoqa.com";
    }
}


