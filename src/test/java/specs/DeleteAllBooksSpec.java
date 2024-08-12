package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class DeleteAllBooksSpec {
    public static RequestSpecification deleteAllBooksRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().method()
            .log().headers()
            .contentType(JSON);
    public static ResponseSpecification deleteAllBooksResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(STATUS)
            .build();
}
