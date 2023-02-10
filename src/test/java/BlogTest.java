import dto.GetResponse;
import dto.MyPostResponse;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BlogTest extends AbstractTest{

    @Order(1)
    @Test
    void authTest(){
        Specifications.initSpecification(Specifications.requestAuthSpec());
        GetResponse getResponse = given()
                .when()
                .post(getBaseUrl() + "gateway/login")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body().as(GetResponse.class);

        Specifications.setToken(getResponse.getToken());
    }

    @Test
    void myPostPage1Test(){
        Specifications.initSpecification(Specifications.requestMyPost());
        MyPostResponse myPostResponse = given()
                .queryParam("page", 1)
                .when()
                .get(getBaseUrl() + "api/posts")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body().as(MyPostResponse.class);
        assertThat(myPostResponse.getData().get(0).getTitle(), containsString("Мейн-Кун"));
    }
}