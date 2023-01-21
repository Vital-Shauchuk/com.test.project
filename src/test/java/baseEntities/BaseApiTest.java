package baseEntities;

import core.ReadProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.projectModels.Project;
import models.suitesModels.Suite;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.protocol.HTTP;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.given;

public class BaseApiTest {
    protected final String projectName = RandomStringUtils.randomAlphanumeric(8);
    protected final String projectCode = RandomStringUtils.randomAlphabetic(5);
    protected Project expProject;
    protected Suite expSuite;
    protected String suiteTitle;
    protected String suiteDesc;
    protected String suitePreconds;

    @BeforeClass
    public void prepareApiData() {

        suiteTitle = RandomStringUtils.randomAlphabetic(10);
        suiteDesc = RandomStringUtils.randomAlphanumeric(10);
        suitePreconds = RandomStringUtils.randomAlphabetic(10);

        expProject = Project.builder()
                .title(projectName)
                .code(projectCode)
                .description("any description")
                .build();

        expSuite = Suite.builder()
                .title(suiteTitle)
                .description(suiteDesc)
                .preconditions(suitePreconds)
                .build();
    }

    @BeforeTest
    public void setup() {

        RestAssured.baseURI = ReadProperties.getInstance().getAPI_URL();

        RestAssured.requestSpecification = given()
                .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                .header("Token", ReadProperties.getInstance().getAPI_Key());
    }
}
