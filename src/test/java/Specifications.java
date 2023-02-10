import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.Setter;

public class Specifications extends AbstractTest{

    @Setter
    private static String token;

    public static RequestSpecification requestAuthSpec(){
        return new RequestSpecBuilder()
                .addFormParam("username", getUsername())
                .addFormParam("password", getPassword())
                .build();
    }

    public static RequestSpecification requestMyPost(){
        return new RequestSpecBuilder()
                .removeFormParam("username")
                .removeFormParam("password")
                .addHeader("X-Auth-Token", token)
                .addQueryParam("sort", "createdTo")
                .addQueryParam("order", "ASC")
                .build();
    }

    public static void initSpecification(RequestSpecification request){
        RestAssured.requestSpecification = request;
    }
}