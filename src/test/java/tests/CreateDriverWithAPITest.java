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

    String token = "Access=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJBY2Nlc3MiLCJhbGciOiJIUzI1NiJ9LCJleHAiOjE3ODA2MTQyNjJ9.5gkWEvwQ6WehIiKZlUHHpHPTmrUrHiWr41EAOtOOMsw; Refresh=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJSZWZyZXNoIiwiYWxnIjoiSFMyNTYifSwiZXhwIjoxNzgwNjE0MjYyfQ.qXCq2EN_Wx8phvDxeZH5OW9t7lR7tLhm6H8r-h-cINo";

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
                .and().headers("Cookie", token)
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(createDriverRequest)
                .when().post("/drivers");

        postResponse.then().log().all();
        Assert.assertEquals(postResponse.getStatusCode(), 200);
    }

    @Test(groups = {"regression", "api"})
    public void createDriverWithAPITestOneLetterName() {

        CreateDriverRequest createDriverRequest = getDriverObject();
        createDriverRequest.setFull_name("l");

        Response postResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", token)
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(createDriverRequest)
                .when().post("/drivers");
        postResponse.then().log().all();
        Assert.assertEquals(postResponse.getStatusCode(), 200);
    }

    @Test(groups = {"regression", "api"})
    public void createDriverWithAPITestMore50CharactersName() {

        CreateDriverRequest createDriverRequest = getDriverObject();
        createDriverRequest.setFull_name("Ligi Mario gsdijddkjdsdabasjkbasbniancilnaklnaklvnamnalgfegdgdvmlvmlasmuiuw");

        Response postResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", token)
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
                .and().headers("Cookie", token)
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(createDriverRequest)
                .when().post("/drivers");
        postResponse.then().log().all();
        Assert.assertEquals(postResponse.getStatusCode(), 422);
    }

    @Test(groups = {"regression", "api"})
    public void createDriverWithAPITestEmptyFullName() {

        CreateDriverRequest createDriverRequest = getDriverObject();
        createDriverRequest.setFull_name("");

        Response postResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", token)
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
                .and().headers("Cookie", token)
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(createDriverRequest)
                .when().post("/drivers");
        postResponse.then().log().all();
        Assert.assertEquals(postResponse.getStatusCode(), 422);
    }


    @Test(groups = {"regression", "api"})
    public void createDriverWithEmptyDateInTheDriverLicense() {
        CreateDriverRequest createDriverRequest = getDriverObject();
        createDriverRequest.setDriving_license_exp("");

        Response postResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", token)
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(createDriverRequest)
                .when().post("/drivers");
        postResponse.then().log().all();
        Assert.assertEquals(postResponse.getStatusCode(), 422);
    }

    @Test(groups = {"regression", "api"})
    public void createDriverWithIncorrectDateInTheMedicalCertification() {
        CreateDriverRequest createDriverRequest = getDriverObject();
        createDriverRequest.setMedical_certification_exp("2025-06-06");

        Response postResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", token)
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(createDriverRequest)
                .when().post("/drivers");
        postResponse.then().log().all();
        Assert.assertEquals(postResponse.getStatusCode(), 422);
    }


    @Test(groups = {"regression", "api"})
    public void createDriverWithEmptyDateInTheMedicalCertification() {
        CreateDriverRequest createDriverRequest = getDriverObject();
        createDriverRequest.setMedical_certification_exp("");

        Response postResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", token)
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
        Response createDriverResponse = createDriver();
        DriverResponse responseObject = createDriverResponse.body().as(DriverResponse.class);
        Integer driverId = responseObject.getId();

        // 2. Update Driver
        UpdateDriverRequest updateDriverRequest = getUpdateDriverObject();
        updateDriverRequest.setId(driverId);
        String expectedName = "John Doe - " + new Random().nextInt();
        updateDriverRequest.setFull_name(expectedName);

        Response putResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", token)
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(updateDriverRequest)
                .when().put("/drivers/" + driverId);

        putResponse.then().log().all();
        // 3. Validate driver name is updated and response has 200 status code
        Assert.assertEquals(putResponse.getStatusCode(), 200);
        String actualName = putResponse.body().jsonPath().getString("full_name");
        Assert.assertEquals(actualName, expectedName);
    }

    public Response createDriver() {
        CreateDriverRequest createDriverRequest = getDriverObject();

        Response postResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", token)
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(createDriverRequest)
                .when().post("/drivers");
        postResponse.then().log().all();
        return postResponse;
    }


    @Test(groups = {"regression", "smoke", "api"})
    public void updateDriverWithEmptyName() {
        // 1. Create driver
        Response createDriverResponse = createDriver();
        DriverResponse responseObject = createDriverResponse.body().as(DriverResponse.class);
        Integer driverId = responseObject.getId();

        // 2. Update Driver
        UpdateDriverRequest updateDriverRequest = getUpdateDriverObject();
        updateDriverRequest.setId(driverId);
        String expectedName = "" + new Random().nextInt();
        updateDriverRequest.setFull_name(expectedName);

        Response putResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", token)
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(updateDriverRequest)
                .when().put("/drivers/" + driverId);

        putResponse.then().log().all();
        // 3. Validate driver name is updated and response has 200 status code
        Assert.assertEquals(putResponse.getStatusCode(), 422);
        String actualName = putResponse.body().jsonPath().getString("full_name");
        Assert.assertEquals(actualName, expectedName);
    }

    @Test(groups = {"regression", "api"})
    public void updateDriverWithNameMore50Characters() {
        // 1. Create driver
        Response createDriverResponse = createDriver();
        DriverResponse responseObject = createDriverResponse.body().as(DriverResponse.class);
        Integer driverId = responseObject.getId();

        // 2. Update Driver
        UpdateDriverRequest updateDriverRequest = getUpdateDriverObject();
        updateDriverRequest.setId(driverId);
        String expectedName = "qwertyuioppoiuytrewqqwertyuioppoiuytrewqwertyuioppoiuytrewqqwertyuiop" + new Random().nextInt();
        updateDriverRequest.setFull_name(expectedName);

        Response putResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", token)
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(updateDriverRequest)
                .when().put("/drivers/" + driverId);

        putResponse.then().log().all();
        // 3. Validate driver name is updated and response has 200 status code
        Assert.assertEquals(putResponse.getStatusCode(), 422);
        String expectedErrorMessage = "String should have at most 50 characters";
        String errorMessage = putResponse.body().jsonPath().getString("detail[0].msg");
        Assert.assertEquals(errorMessage, expectedErrorMessage);
    }


    @Test(groups = {"regression", "api"})
    public void updateDriverWithNameMinCharacters() {
        // 1. Create driver
        Response createDriverResponse = createDriver();
        DriverResponse responseObject = createDriverResponse.body().as(DriverResponse.class);
        Integer driverId = responseObject.getId();

        // 2. Update Driver
        UpdateDriverRequest updateDriverRequest = getUpdateDriverObject();
        updateDriverRequest.setId(driverId);
        String expectedName = "S";
        updateDriverRequest.setFull_name(expectedName);

        Response putResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", token)
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(updateDriverRequest)
                .when().put("/drivers/" + driverId);

        putResponse.then().log().all();
        // 3. Validate driver name is updated and response has 200 status code
        Assert.assertEquals(putResponse.getStatusCode(), 200);
        String actualName = putResponse.body().jsonPath().getString("full_name");
        Assert.assertEquals(actualName, expectedName);
    }

    @Test(groups = {"regression", "smoke", "api"})
    public void updateDriverMedical_CertificationWithWrongDate() {
        // 1. Create driver
        Response createDriverResponse = createDriver();
        DriverResponse responseObject = createDriverResponse.body().as(DriverResponse.class);
        Integer driverId = responseObject.getId();

        // 2. Update Driver
        UpdateDriverRequest updateDriverRequest = getUpdateDriverObject();
        updateDriverRequest.setId(driverId);
        String mExpOldDate = "2025-07-01";
        updateDriverRequest.setMedical_certification_exp(mExpOldDate);

        Response putResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", token)
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(updateDriverRequest)
                .when().put("/drivers/" + driverId);

        putResponse.then().log().all();
        // 3. Validate driver name is updated and response has 200 status code
        Assert.assertEquals(putResponse.getStatusCode(), 422);
    }


    @Test(groups = {"regression", "smoke", "api"})
    public void updateDriverMedical_CertificationWithEmptyDate() {
        // 1. Create driver
        Response createDriverResponse = createDriver();
        DriverResponse responseObject = createDriverResponse.body().as(DriverResponse.class);
        Integer driverId = responseObject.getId();

        // 2. Update Driver
        UpdateDriverRequest updateDriverRequest = getUpdateDriverObject();
        updateDriverRequest.setId(driverId);
        String mExpemptyDate = "";
        updateDriverRequest.setMedical_certification_exp(mExpemptyDate);

        Response putResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", token)
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(updateDriverRequest)
                .when().put("/drivers/" + driverId);

        putResponse.then().log().all();
        // 3. Validate driver name is updated and response has 200 status code
        Assert.assertEquals(putResponse.getStatusCode(), 422);
    }

    @Test(groups = {"regression", "smoke", "api"})
    public void updateDriverLicenseWithWrongDate() {
        // 1. Create driver
        Response createDriverResponse = createDriver();
        DriverResponse responseObject = createDriverResponse.body().as(DriverResponse.class);
        Integer driverId = responseObject.getId();

        // 2. Update Driver
        UpdateDriverRequest updateDriverRequest = getUpdateDriverObject();
        updateDriverRequest.setId(driverId);
        String LicDriverDate = "2025-07-01";
        updateDriverRequest.setDriving_license_exp(LicDriverDate);

        Response putResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", token)
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(updateDriverRequest)
                .when().put("/drivers/" + driverId);

        putResponse.then().log().all();
        // 3. Validate driver name is updated and response has 200 status code
        Assert.assertEquals(putResponse.getStatusCode(), 422);
    }


    @Test(groups = {"regression", "smoke", "api"})
    public void updateDriverLicenseWithEmptyDate() {
        // 1. Create driver
        Response createDriverResponse = createDriver();
        DriverResponse responseObject = createDriverResponse.body().as(DriverResponse.class);
        Integer driverId = responseObject.getId();

        // 2. Update Driver
        UpdateDriverRequest updateDriverRequest = getUpdateDriverObject();
        updateDriverRequest.setId(driverId);
        String LicDriverDate = "";
        updateDriverRequest.setDriving_license_exp(LicDriverDate);

        Response putResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", token)
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(updateDriverRequest)
                .when().put("/drivers/" + driverId);

        putResponse.then().log().all();
        // 3. Validate driver name is updated and response has 200 status code
        Assert.assertEquals(putResponse.getStatusCode(), 422);
    }


    @Test(groups = {"regression", "smoke", "api"})
    public void updateDriverLicenseWithInvalidDate() {
        // 1. Create driver
        Response createDriverResponse = createDriver();
        DriverResponse responseObject = createDriverResponse.body().as(DriverResponse.class);
        Integer driverId = responseObject.getId();

        // 2. Update Driver
        UpdateDriverRequest updateDriverRequest = getUpdateDriverObject();
        updateDriverRequest.setId(driverId);
        String LicDriverDate = "2025-04-04";
        updateDriverRequest.setDriving_license_exp(LicDriverDate);

        Response putResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", token)
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(updateDriverRequest)
                .when().put("/drivers/" + driverId);

        putResponse.then().log().all();
        // 3. Validate driver name is updated and response has 200 status code
        Assert.assertEquals(putResponse.getStatusCode(), 422);
    }

    @Test(groups = {"regression", "smoke", "api"})
    public void getDriversApiSize100QueryParamsTest() {

        Response getResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", token)
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().queryParam("size", 100)
                .and().log().all()
                .when().get("/drivers");
        getResponse.then().log().all();

        Assert.assertEquals(getResponse.statusCode(), 200);
        List<Integer> driverIds = getResponse.body().jsonPath().getList("items.id");
        Assert.assertEquals(driverIds.size(), 100);
    }

    @Test(groups = {"regression", "smoke", "api"})
    public void getDriversApiSize50QueryParamsTest() {

        Response getResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", token)
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().queryParam("size", 50)
                .and().log().all()
                .when().get("/drivers");
        getResponse.then().log().all();

        Assert.assertEquals(getResponse.statusCode(), 200);
        List<Integer> driverIds = getResponse.body().jsonPath().getList("items.id");
        Assert.assertEquals(driverIds.size(), 50);
    }

    @Test(groups = {"regression", "smoke", "api"})
    public void getDriversApiSizeNegativeQueryParamsTest() {

        Response getResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", token)
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().queryParam("size", -1)
                .and().log().all()
                .when().get("/drivers");
        getResponse.then().log().all();

        Assert.assertEquals(getResponse.statusCode(), 422);
        String actualErrorMessage = getResponse.body().jsonPath().getString("detail[0].msg");
        Assert.assertEquals(actualErrorMessage, "Input should be greater than or equal to 1");
    }


    @Test(groups = {"regression", "smoke", "api"})
    public void getDriversApiSize101NegativeQueryParamsTest() {

        Response getResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", token)
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().queryParam("size", 101)
                .and().log().all()
                .when().get("/drivers");
        getResponse.then().log().all();

        Assert.assertEquals(getResponse.statusCode(), 422);
        String actualErrorMessage = getResponse.body().jsonPath().getString("detail[0].msg");
        Assert.assertEquals(actualErrorMessage, "Input should be less than or equal to 100");
    }
}
