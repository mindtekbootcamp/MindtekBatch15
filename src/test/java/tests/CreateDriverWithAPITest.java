package tests;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class CreateDriverWithAPITest  {

@Test(groups = {"regression","smoke"})
public void CreateDriverWithAPITest() {
    Response postResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
            .and().headers("Cookie", "Access=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJBY2Nlc3MiLCJhbGciOiJIUzI1NiJ9LCJleHAiOjE3Nzg3OTc5MTN9.IjewDyyQh8UOgPMlhV3WtbzECCaoWFMR9YY0xm7Er54; Refresh=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJSZWZyZXNoIiwiYWxnIjoiSFMyNTYifSwiZXhwIjoxNzc4Nzk3OTEzfQ.5C7LGef-5wU3GzpKb8qe4_ql8wNVMVfdwGEpl1YFec0")
            .and().header("Content-Type", "application/json")
            .and().header("Accept", "application/json")
            .and().body("{\n" +
                    "                                \"full_name\": \"John Doe\",\n" +
                    "                \"logbook_email\": \"\",\n" +
                    "                \"logbook_password\": \"\",\n" +
                    "                \"is_staff\": true,\n" +
                    "                \"is_local\": false,\n" +
                    "                \"twic\": false,\n" +
                    "                \"driving_license_exp\": \"2026-06-06\",\n" +
                    "                \"medical_certification_exp\": \"2026-07-01\",\n" +
                    "                \"contacts_phone\": [],\n" +
                    "        \"contacts_viber\": [],\n" +
                    "        \"contacts_other\": []\n" +
                    "}")
            .when().post("/drivers");
    String driverId = postResponse.body().jsonPath().getString("id");
    postResponse.then().log().all();

     }
     @Test
    public void CreateDriverWithAPIEmptyName(){
         Response postResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                 .and().headers("Cookie", "Access=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJBY2Nlc3MiLCJhbGciOiJIUzI1NiJ9LCJleHAiOjE3Nzg3OTc5MTN9.IjewDyyQh8UOgPMlhV3WtbzECCaoWFMR9YY0xm7Er54; Refresh=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJSZWZyZXNoIiwiYWxnIjoiSFMyNTYifSwiZXhwIjoxNzc4Nzk3OTEzfQ.5C7LGef-5wU3GzpKb8qe4_ql8wNVMVfdwGEpl1YFec0")
                 .and().header("Content-Type", "application/json")
                 .and().header("Accept", "application/json")
                 .and().body("{\n" +
                         "                \"full_name\": \"\",\n" +
                         "                \"logbook_email\": \"\",\n" +
                         "                \"logbook_password\": \"\",\n" +
                         "                \"is_staff\": true,\n" +
                         "                \"is_local\": false,\n" +
                         "                \"twic\": false,\n" +
                         "                \"driving_license_exp\": \"2026-06-06\",\n" +
                         "                \"medical_certification_exp\": \"2026-07-01\",\n" +
                         "                \"contacts_phone\": [],\n" +
                         "        \"contacts_viber\": [],\n" +
                         "        \"contacts_other\": []\n" +
                         "}")
                 .when().post("/drivers");
         String driverId = postResponse.body().jsonPath().getString("id");
         postResponse.then().log().all();

     }
    @Test
    public void CreateDriverWithAPIOneLetterName(){
        Response postResponse = given().baseUri("https://api.app.elarlogistics.com/services/elar-saas/api/v3")
                .and().headers("Cookie", "Access=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJBY2Nlc3MiLCJhbGciOiJIUzI1NiJ9LCJleHAiOjE3Nzg3OTc5MTN9.IjewDyyQh8UOgPMlhV3WtbzECCaoWFMR9YY0xm7Er54; Refresh=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHVkZW50QG1pbmR0ZWsuY29tIiwiaGVhZGVyIjp7InR5cGUiOiJSZWZyZXNoIiwiYWxnIjoiSFMyNTYifSwiZXhwIjoxNzc4Nzk3OTEzfQ.5C7LGef-5wU3GzpKb8qe4_ql8wNVMVfdwGEpl1YFec0")
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body("{\n" +
                        "                \"full_name\": \"^%&$^$#\",\n" +
                        "                \"logbook_email\": \"\",\n" +
                        "                \"logbook_password\": \"\",\n" +
                        "                \"is_staff\": true,\n" +
                        "                \"is_local\": false,\n" +
                        "                \"twic\": false,\n" +
                        "                \"driving_license_exp\": \"2026-06-06\",\n" +
                        "                \"medical_certification_exp\": \"2026-07-01\",\n" +
                        "                \"contacts_phone\": [],\n" +
                        "        \"contacts_viber\": [],\n" +
                        "        \"contacts_other\": []\n" +
                        "}")
                .when().post("/drivers");
        String driverId = postResponse.body().jsonPath().getString("id");
        postResponse.then().log().all();


    }
}