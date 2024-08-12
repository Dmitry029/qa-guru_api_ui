package tests;

import authorization.AuthorizationApi;
import helpers.WithLogin;
import io.restassured.response.Response;
import models.BookDataModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static specs.AddBookSpec.addBookRequestSpec;
import static specs.AddBookSpec.addBookResponseSpec;
import static specs.DeleteAllBooksSpec.deleteAllBooksRequestSpec;
import static specs.DeleteAllBooksSpec.deleteAllBooksResponseSpec;

public class DeleteBookTest extends BaseTest {

    private final Response authResponse = new AuthorizationApi().getAuthorizationResponse();

    @WithLogin
    @Test
    void deleteBookTest() {

        //*****************************************************************************************
        given(deleteAllBooksRequestSpec)
                .header("Authorization", "Bearer " + authResponse.path("token"))
                .queryParams("UserId", authResponse.path("userId"))
                .when()
                .delete("BookStore/v1/Books")
                .then()
                .spec(deleteAllBooksResponseSpec)
                .extract().response();

//******************************************************************************************
        String isbn = "9781449325862";
        List<String> books = List.of(isbn);
        BookDataModel bookData = new BookDataModel(authResponse.path("userId"), books);

        Response addBookResponse = given(addBookRequestSpec)
                .header("Authorization", "Bearer " + authResponse.path("token"))
                .body(bookData)
                .when()
                .post("BookStore/v1/Books")
                .then()
                .spec(addBookResponseSpec)
                .extract().response();
    }
}
