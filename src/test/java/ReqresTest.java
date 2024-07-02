import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ReqresTest {
    private final static String URL = "https://reqres.in/";

    @Test
    public void checkUnSuccessfulRegister() {
        Specification.installSpecification(Specification.requestSpecification(URL), Specification.responseSpecificationStatusCode400());
        String error = "Missing password";
        Register user = new Register("sydney@fife", "");
        UnSeccessfulRegister unSeccessfulRegister = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().body().as(UnSeccessfulRegister.class);

        Assert.assertNotNull(unSeccessfulRegister.getError());

        Assert.assertEquals(unSeccessfulRegister.getError(), error);
    }
}
