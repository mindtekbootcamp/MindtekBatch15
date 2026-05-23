package tests;

import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.CreateDriverRequest;
import pojos.DriverResponse;
import pojos.UpdateDriverRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

@Getter
@Setter
public class CreateDriverWithAPITest {

    public CreateDriverRequest getDriverObject() {
        CreateDriverRequest createDriverRequest = new CreateDriverRequest();

        createDriverRequest.setFull_name("Luigi Mario");
        createDriverRequest.setDriving_license_exp("2026-06-06");
        createDriverRequest.setMedical_certification_exp("2026-07-01");
        createDriverRequest.setIs_local(false);
        createDriverRequest.setIs_staff(true);
        createDriverRequest.setTwic(false);
        List<String> contacts = new ArrayList<>();
        createDriverRequest.setContacts_phone(contacts);
        createDriverRequest.setContacts_viber(contacts);
        createDriverRequest.setContacts_other(contacts);
        return createDriverRequest;
    }

    public UpdateDriverRequest getUpdateDriverObject() {
        UpdateDriverRequest updateDriverRequest = new UpdateDriverRequest();
        updateDriverRequest.setFull_name("Luigi Mario");
        updateDriverRequest.setDriving_license_exp("2026-06-06");
        updateDriverRequest.setMedical_certification_exp("2026-07-01");
        updateDriverRequest.setIs_local(false);
        updateDriverRequest.setIs_staff(true);
        updateDriverRequest.setTwic(false);
        updateDriverRequest.setUuid("e72fa343-5973-4a9e-9b8c-b211d1d1c231");
        updateDriverRequest.setStatus("Off duty");
        return updateDriverRequest;
    }

    @Test(groups = {"regression", "smoke", "api"})
    public void createDriverWithAPITestPositive() {

        CreateDriverRequest createDriverRequest = getDriverObject();
        Response postResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", "Access=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJBY2Nlc3MiLCJhbGciOiJIUzI1NiJ9LCJleHAiOjE3NzkwMjg5ODV9.4vw7Gr9H4FYtpYV7iMVxUg-RrhmpGtFkSWl_gpmkeSM; Refresh=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJSZWZyZXNoIiwiYWxnIjoiSFMyNTYifSwiZXhwIjoxNzc5MDI4OTg1fQ.4hfqq6MDfg_IesmXrL5G6C5UoTFnBW_KFrpumzVY0bE")
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(createDriverRequest)
                .when().post("/drivers");

        postResponse.then().log().all();
        Assert.assertEquals(postResponse.getStatusCode(), 200);
    }

    @Test(groups = {"regression", "smoke", "api"})
    public void createDriverWithAPITestOneLetterName() {

        CreateDriverRequest createDriverRequest = getDriverObject();
        createDriverRequest.setFull_name("l");

        Response postResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", "Access=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJBY2Nlc3MiLCJhbGciOiJIUzI1NiJ9LCJleHAiOjE3NzkwMjg5ODV9.4vw7Gr9H4FYtpYV7iMVxUg-RrhmpGtFkSWl_gpmkeSM; Refresh=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJSZWZyZXNoIiwiYWxnIjoiSFMyNTYifSwiZXhwIjoxNzc5MDI4OTg1fQ.4hfqq6MDfg_IesmXrL5G6C5UoTFnBW_KFrpumzVY0bE")
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(createDriverRequest)
                .when().post("/drivers");
        postResponse.then().log().all();
        Assert.assertEquals(postResponse.getStatusCode(), 200);
    }

    @Test(groups = {"regression", "smoke", "api"})
    public void createDriverWithAPITestMore50CharactersName() {

        CreateDriverRequest createDriverRequest = getDriverObject();
        createDriverRequest.setFull_name("Ligi Mario gsdijddkjdsdabasjkbasbniancilnaklnaklvnamnalgfegdgdvmlvmlasmuiuw");

        Response postResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", "Access=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJBY2Nlc3MiLCJhbGciOiJIUzI1NiJ9LCJleHAiOjE3NzkwMjg5ODV9.4vw7Gr9H4FYtpYV7iMVxUg-RrhmpGtFkSWl_gpmkeSM; Refresh=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJSZWZyZXNoIiwiYWxnIjoiSFMyNTYifSwiZXhwIjoxNzc5MDI4OTg1fQ.4hfqq6MDfg_IesmXrL5G6C5UoTFnBW_KFrpumzVY0bE")
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(createDriverRequest)
                .when().post("/drivers");

        postResponse.then().log().all();
        Assert.assertEquals(postResponse.getStatusCode(), 422);
    }

    @Test(groups = {"regression", "smoke", "api"})
    public void createDriverWithAPITestEspecialCharacters() {

        CreateDriverRequest createDriverRequest = getDriverObject();
        createDriverRequest.setFull_name("$%#3");

        Response postResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", "Access=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJBY2Nlc3MiLCJhbGciOiJIUzI1NiJ9LCJleHAiOjE3NzkwMjg5ODV9.4vw7Gr9H4FYtpYV7iMVxUg-RrhmpGtFkSWl_gpmkeSM; Refresh=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJSZWZyZXNoIiwiYWxnIjoiSFMyNTYifSwiZXhwIjoxNzc5MDI4OTg1fQ.4hfqq6MDfg_IesmXrL5G6C5UoTFnBW_KFrpumzVY0bE")
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(createDriverRequest)
                .when().post("/drivers");
        postResponse.then().log().all();
        Assert.assertEquals(postResponse.getStatusCode(), 422);
    }

    @Test(groups = {"regression", "smoke", "api"})
    public void createDriverWithAPITestEmptyFullName() {

        CreateDriverRequest createDriverRequest = getDriverObject();
        createDriverRequest.setFull_name("");

        Response postResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", "Access=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJBY2Nlc3MiLCJhbGciOiJIUzI1NiJ9LCJleHAiOjE3NzkzMjI5NDh9.n6EwBYYRxT7dssqvmqxmpSC6H32qUoz02qU04tPloVE; Refresh=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJSZWZyZXNoIiwiYWxnIjoiSFMyNTYifSwiZXhwIjoxNzc5MzIyOTQ4fQ.u8IlS3rnMo3Gyz9h8SERJPJVDUcZ0v7smZ0IId9YUTs")
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(createDriverRequest)
                .when().post("/drivers");
        postResponse.then().log().all();
        Assert.assertEquals(postResponse.getStatusCode(), 422);
    }

    @Test(groups = {"regression", "smoke", "api"})
    public void createDriverWithIncorrectDateInTheDriverLicense() {
        CreateDriverRequest createDriverRequest = getDriverObject();
        createDriverRequest.setDriving_license_exp("2025-06-06");

        Response postResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", "Access=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJBY2Nlc3MiLCJhbGciOiJIUzI1NiJ9LCJleHAiOjE3NzkwMjg5ODV9.4vw7Gr9H4FYtpYV7iMVxUg-RrhmpGtFkSWl_gpmkeSM; Refresh=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJSZWZyZXNoIiwiYWxnIjoiSFMyNTYifSwiZXhwIjoxNzc5MDI4OTg1fQ.4hfqq6MDfg_IesmXrL5G6C5UoTFnBW_KFrpumzVY0bE")
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(createDriverRequest)
                .when().post("/drivers");
        postResponse.then().log().all();
        Assert.assertEquals(postResponse.getStatusCode(), 422);
    }


    @Test(groups = {"regression", "smoke", "api"})
    public void createDriverWithEmptyDateInTheDriverLicense() {
        CreateDriverRequest createDriverRequest = getDriverObject();
        createDriverRequest.setDriving_license_exp("");

        Response postResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", "Access=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJBY2Nlc3MiLCJhbGciOiJIUzI1NiJ9LCJleHAiOjE3NzkzMjI5NDh9.n6EwBYYRxT7dssqvmqxmpSC6H32qUoz02qU04tPloVE; Refresh=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJSZWZyZXNoIiwiYWxnIjoiSFMyNTYifSwiZXhwIjoxNzc5MzIyOTQ4fQ.u8IlS3rnMo3Gyz9h8SERJPJVDUcZ0v7smZ0IId9YUTs")
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(createDriverRequest)
                .when().post("/drivers");
        postResponse.then().log().all();
        Assert.assertEquals(postResponse.getStatusCode(), 422);
    }

    @Test(groups = {"regression", "smoke", "api"})
    public void createDriverWithIncorretDateInTheMedicalCertification() {
        CreateDriverRequest createDriverRequest = getDriverObject();
        createDriverRequest.setMedical_certification_exp("2025-06-06");

        Response postResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", "Access=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJBY2Nlc3MiLCJhbGciOiJIUzI1NiJ9LCJleHAiOjE3NzkwMjg5ODV9.4vw7Gr9H4FYtpYV7iMVxUg-RrhmpGtFkSWl_gpmkeSM; Refresh=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJSZWZyZXNoIiwiYWxnIjoiSFMyNTYifSwiZXhwIjoxNzc5MDI4OTg1fQ.4hfqq6MDfg_IesmXrL5G6C5UoTFnBW_KFrpumzVY0bE")
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(createDriverRequest)
                .when().post("/drivers");
        postResponse.then().log().all();
        Assert.assertEquals(postResponse.getStatusCode(), 422);
    }


    @Test(groups = {"regression", "smoke", "api"})
    public void createDriverWithEmptyDateInTheMedicalCertification() {
        CreateDriverRequest createDriverRequest = getDriverObject();
        createDriverRequest.setMedical_certification_exp("");

        Response postResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", "Access=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJBY2Nlc3MiLCJhbGciOiJIUzI1NiJ9LCJleHAiOjE3Nzk0OTI4NzV9.6swRGysNFnsY5swgur2jbKdr3QI7q7ZPRfyCqRS1o-c; Refresh=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJSZWZyZXNoIiwiYWxnIjoiSFMyNTYifSwiZXhwIjoxNzc5NDkyODc1fQ.Fr1FdjsMsPATMtkCQQ7Bcq3JykZrb2OkWnkMAdoUWgs")
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(createDriverRequest)
                .when().post("/drivers");
        postResponse.then().log().all();
        Assert.assertEquals(postResponse.getStatusCode(), 422);
    }



    @Test(groups = {"regression", "smoke", "api"})
    public void updateDriverWithAPITestPositive() {
        // 1. Create driver
        Response createDriverResponse=createDriver();
        DriverResponse responseObject=createDriverResponse.body().as(DriverResponse.class);
        Integer driverId = responseObject.getId();

        // 2. Update Driver
        UpdateDriverRequest updateDriverRequest = getUpdateDriverObject();
        updateDriverRequest.setId(driverId);
        String expectedName="John Doe - "+new Random().nextInt();
        updateDriverRequest.setFull_name(expectedName);

        Response putResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", "Access=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJBY2Nlc3MiLCJhbGciOiJIUzI1NiJ9LCJleHAiOjE3Nzk0OTI4NzV9.6swRGysNFnsY5swgur2jbKdr3QI7q7ZPRfyCqRS1o-c; Refresh=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJSZWZyZXNoIiwiYWxnIjoiSFMyNTYifSwiZXhwIjoxNzc5NDkyODc1fQ.Fr1FdjsMsPATMtkCQQ7Bcq3JykZrb2OkWnkMAdoUWgs")
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(updateDriverRequest)
                .when().put("/drivers/" + driverId);

        putResponse.then().log().all();
        // 3. Validate driver name is updated and response has 200 status code
        Assert.assertEquals(putResponse.getStatusCode(), 200);
        String actualName = putResponse.body().jsonPath().getString("full_name");
        Assert.assertEquals(actualName,expectedName);
    }

    public Response createDriver(){
        CreateDriverRequest createDriverRequest = getDriverObject();

        Response postResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", "Access=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJBY2Nlc3MiLCJhbGciOiJIUzI1NiJ9LCJleHAiOjE3Nzk0OTI4NzV9.6swRGysNFnsY5swgur2jbKdr3QI7q7ZPRfyCqRS1o-c; Refresh=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJSZWZyZXNoIiwiYWxnIjoiSFMyNTYifSwiZXhwIjoxNzc5NDkyODc1fQ.Fr1FdjsMsPATMtkCQQ7Bcq3JykZrb2OkWnkMAdoUWgs")
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(createDriverRequest)
                .when().post("/drivers");
        postResponse.then().log().all();
        return postResponse;
    }
}