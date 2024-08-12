package tests;

import authorization.AuthorizationApi;
import helpers.WithLogin;
import io.restassured.response.Response;
import models.BookDataModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static specs.AddBookSpec.addBookRequestSpec;
import static specs.AddBookSpec.addBookResponseSpec;
import static specs.DeleteAllBooksSpec.deleteAllBooksRequestSpec;
import static specs.DeleteAllBooksSpec.deleteAllBooksResponseSpec;

@Tag("regression")
@Tag("smoke")
public class DeleteBookTest extends BaseTest {
    private final Response authResponse = new AuthorizationApi().getAuthorizationResponse();

    @Test
    @WithLogin
    @DisplayName("Проверить, что при вводе неправильного ISBN появляется ошибка")
    void negative400AddBookToCollectionTest() {

        step("Delete all books", () -> given(deleteAllBooksRequestSpec)
                .header("Authorization", "Bearer " + authResponse.path("token"))
                .queryParams("UserId", authResponse.path("userId"))
                .when()
                .delete("BookStore/v1/Books")
                .then()
                .spec(deleteAllBooksResponseSpec));

        String isbn = "9781449325862";
        List<String> books = List.of(isbn);
        BookDataModel bookData = new BookDataModel(authResponse.path("userId"), books);

        step("Add book to profile. Check 400 error", () -> given(addBookRequestSpec)
                .header("Authorization", "Bearer " + authResponse.path("token"))
                .body(bookData)
                .when()
                .post("BookStore/v1/Books")
                .then()
                .spec(addBookResponseSpec)
                .body("code", is("1205"))
                .body("message", is("ISBN supplied is not available in Books Collection!")));
    }
}
