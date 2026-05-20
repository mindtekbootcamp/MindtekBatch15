package tests;

import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.CreateDriverRequest;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;
@Getter
@Setter

public class CreateDriverWithAPITest {

    public CreateDriverRequest getDriverObject(){
        CreateDriverRequest createDriverRequest = new CreateDriverRequest();

        createDriverRequest.setFull_name("Luigi Mario");
        createDriverRequest.setDriving_license_exp("2026-06-06");
        createDriverRequest.setMedical_certification_exp("2026-07-01");
        createDriverRequest.setIs_local(false);
        createDriverRequest.setIs_staff(true);
        createDriverRequest.setTwic(false);
        List<String> contacts=new ArrayList<>();
        createDriverRequest.setContacts_phone(contacts);
        createDriverRequest.setContacts_viber(contacts);
        createDriverRequest.setContacts_other(contacts);
        return createDriverRequest;
    }
    @Test(groups = {"regression","smoke","api"})
    public void CreateDriverWithAPITestPositive(){
        CreateDriverRequest createDriverRequest = getDriverObject();
        Response postResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
            .and().headers("Cookie", "Access=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJBY2Nlc3MiLCJhbGciOiJIUzI1NiJ9LCJleHAiOjE3NzkwMjg5ODV9.4vw7Gr9H4FYtpYV7iMVxUg-RrhmpGtFkSWl_gpmkeSM; Refresh=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJSZWZyZXNoIiwiYWxnIjoiSFMyNTYifSwiZXhwIjoxNzc5MDI4OTg1fQ.4hfqq6MDfg_IesmXrL5G6C5UoTFnBW_KFrpumzVY0bE")
            .and().header("Content-Type", "application/json")
            .and().header("Accept", "application/json")
            .and().body(createDriverRequest)
            .when().post("/drivers");
    String driverId = postResponse.body().jsonPath().getString("id");

    postResponse.then().log().all();
        Assert.assertEquals(postResponse.getStatusCode(),200);
    }

    @Test(groups = {"regression","smoke","api"})
    public void CreateDriverWithAPIOneLetterName(){
        CreateDriverRequest createDriverRequest = getDriverObject();
        createDriverRequest.setFull_name("L");

        Response postResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", "Access=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJBY2Nlc3MiLCJhbGciOiJIUzI1NiJ9LCJleHAiOjE3NzkwMjg5ODV9.4vw7Gr9H4FYtpYV7iMVxUg-RrhmpGtFkSWl_gpmkeSM; Refresh=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJSZWZyZXNoIiwiYWxnIjoiSFMyNTYifSwiZXhwIjoxNzc5MDI4OTg1fQ.4hfqq6MDfg_IesmXrL5G6C5UoTFnBW_KFrpumzVY0bE")
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(createDriverRequest)
                .when().post("/drivers");
        String driverId = postResponse.body().jsonPath().getString("id");

        postResponse.then().log().all();
        Assert.assertEquals(postResponse.getStatusCode(),200);
    }

    @Test(groups = {"regression","smoke","api"})
    public void CreateDriverWithAPITestMore50CharactersName(){
        CreateDriverRequest createDriverRequest = getDriverObject();
        createDriverRequest.setFull_name("LjvhalhvkndbvbgdxfgsgtfbsbjklvhaljfnvkjsNVkljFbvklhjfbvhjfbcnnKBNvkjfbsvnbLKhjvblhfkbkncx");
        Response postResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", "Access=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJBY2Nlc3MiLCJhbGciOiJIUzI1NiJ9LCJleHAiOjE3NzkwMjg5ODV9.4vw7Gr9H4FYtpYV7iMVxUg-RrhmpGtFkSWl_gpmkeSM; Refresh=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJSZWZyZXNoIiwiYWxnIjoiSFMyNTYifSwiZXhwIjoxNzc5MDI4OTg1fQ.4hfqq6MDfg_IesmXrL5G6C5UoTFnBW_KFrpumzVY0bE")
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(createDriverRequest)
                .when().post("/drivers");
        String driverId = postResponse.body().jsonPath().getString("id");
        postResponse.then().log().all();
        Assert.assertEquals(postResponse.getStatusCode(),422);
    }
    @Test(groups = {"regression","smoke","api"})
    public void CreateDriverWithAPITestWithEspecialCharacters(){
        CreateDriverRequest createDriverRequest = getDriverObject();
        createDriverRequest.setFull_name("&^%$#");
        Response postResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", "Access=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJBY2Nlc3MiLCJhbGciOiJIUzI1NiJ9LCJleHAiOjE3NzkwMjg5ODV9.4vw7Gr9H4FYtpYV7iMVxUg-RrhmpGtFkSWl_gpmkeSM; Refresh=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJSZWZyZXNoIiwiYWxnIjoiSFMyNTYifSwiZXhwIjoxNzc5MDI4OTg1fQ.4hfqq6MDfg_IesmXrL5G6C5UoTFnBW_KFrpumzVY0bE")
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(createDriverRequest)
                .when().post("/drivers");
        String driverId = postResponse.body().jsonPath().getString("id");

        postResponse.then().log().all();
        Assert.assertEquals(postResponse.getStatusCode(),422);
    }
    @Test(groups = {"regression","smoke","api"})
    public void createDriverWithIncorrectDataInTheDriverLicense(){
        CreateDriverRequest createDriverRequest = getDriverObject();
        createDriverRequest.setDriving_license_exp("2025-09-07");

        Response postResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", "Access=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJBY2Nlc3MiLCJhbGciOiJIUzI1NiJ9LCJleHAiOjE3NzkwMjg5ODV9.4vw7Gr9H4FYtpYV7iMVxUg-RrhmpGtFkSWl_gpmkeSM; Refresh=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJSZWZyZXNoIiwiYWxnIjoiSFMyNTYifSwiZXhwIjoxNzc5MDI4OTg1fQ.4hfqq6MDfg_IesmXrL5G6C5UoTFnBW_KFrpumzVY0bE")
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(createDriverRequest)
                .when().post("/drivers");
        String driverId = postResponse.body().jsonPath().getString("Data");

        postResponse.then().log().all();
        Assert.assertEquals(postResponse.getStatusCode(),422);
}
    @Test(groups = {"regression","smoke","api"})
    public void createDriverWithIncorrectDataInTheMedicalCertification() {
        CreateDriverRequest createDriverRequest = getDriverObject();
        createDriverRequest.setMedical_certification_exp("2027-07-01");

        Response postResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", "Access=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJBY2Nlc3MiLCJhbGciOiJIUzI1NiJ9LCJleHAiOjE3NzkwMjg5ODV9.4vw7Gr9H4FYtpYV7iMVxUg-RrhmpGtFkSWl_gpmkeSM; Refresh=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJSZWZyZXNoIiwiYWxnIjoiSFMyNTYifSwiZXhwIjoxNzc5MDI4OTg1fQ.4hfqq6MDfg_IesmXrL5G6C5UoTFnBW_KFrpumzVY0bE")
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(createDriverRequest)
                .when().post("/drivers");
        String driverId = postResponse.body().jsonPath().getString("Data");

        postResponse.then().log().all();
        Assert.assertEquals(postResponse.getStatusCode(), 422);

    }


}

