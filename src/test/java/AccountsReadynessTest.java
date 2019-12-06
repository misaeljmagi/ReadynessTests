import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class AccountsReadynessTest {

@Test
    public void isAccountready(){
            String accessToken=given()
                    .contentType(ContentType.JSON)
                    .log().all()
                    .body("{ \n" +
                            "    \"username\": \"bdsolqe@gmail.com\", \n" +
                            "    \"passcode\": \"192837\"\n" +
                            "}")
                    .post("https://api-dev.bdsdigital.com.ar/auth/v1/sign-in")
                    .then()
                    .extract().response().jsonPath().get("accessToken");

            given()
                    .contentType(ContentType.JSON)
                    .log().all()
                    .header("Authorization", "Bearer "+accessToken)
                    .get("https://accounts-dev.bdsdigital.com.ar/accounts/v1/me")
                    .then()
                    .statusCode(200);
}
}
