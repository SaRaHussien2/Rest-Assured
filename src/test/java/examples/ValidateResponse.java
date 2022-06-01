package examples;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class ValidateResponse {

    @Test
    public void validate_response_code_TC1(){
        given().
            get("https://parabank.parasoft.com/parabank/services/bank/customers/12212").
        then().
            statusCode(200);
    }
    @Test
    public void validate_response_code_TC2(){
        Response response = RestAssured.get("https://parabank.parasoft.com/parabank/services/bank/customers/12212");
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void validate_response_data_TC3(){
        given().
            get("https://parabank.parasoft.com/parabank/services/bank/customers/12212").
        then().
                assertThat().body("customer.id", equalTo("12212")).
                and().
                assertThat().body("customer.firstName", equalTo("John")).
                and().
                assertThat().body("customer.lastName", equalTo("Smith"));
    }
    @Test
    public void validate_invalid_response_TC1(){
       String response_msg = RestAssured.get("https://parabank.parasoft.com/parabank/services/bank/customers/12214").asString();
       Assert.assertEquals(response_msg,"Could not find customer #12214");
    }
}
