package examples;

import io.restassured.http.ContentType;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;

public class XMLSchemaValidation {

    @Test
    public void schemaValidation() throws IOException {
        File file = new File("./SoapRequest/Add.xml");
        if (file.exists()) {
            System.out.println("  >>>  File exists");
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        String requestBody = IOUtils.toString(fileInputStream, "UTF-8");

        baseURI = "http://www.dneonline.com";

            given().
                contentType(ContentType.XML)
                .accept(ContentType.XML).
                body(requestBody).
            when().
                post("/calculator.asmx").
            then().
                statusCode(200).log().all()
            .and().
                body("//*:AddResult.text()",equalTo("32"))
            .and().
                assertThat().body(matchesXsdInClasspath("calculator.xsd"));
    }
}
