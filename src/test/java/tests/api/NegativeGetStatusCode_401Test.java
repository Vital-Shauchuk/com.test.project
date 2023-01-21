package tests.api;

import adapters.ProjectsAdapter;
import baseEntities.BaseApiTest;
import endpoints.api.ProjectsEndpoints;
import models.projectModels.GetResponseResult;
import models.projectModels.PostResponseResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class NegativeGetStatusCode_401Test extends BaseApiTest {

    @Test
    public void createProjectsTest() {
        PostResponseResult actProject = new ProjectsAdapter().postCreateProject(expProject);
        Assert.assertEquals(
                actProject.getResult().getCode(),
                expProject.getCode().toUpperCase()
        );
    }

    @Test(dependsOnMethods = "createProjectsTest")
    public void negativeGetProjectByCodeTestWithSC_401() {
        given()
                .when()
                .header("Token", RandomStringUtils.randomNumeric(15))
                .get(String.format(ProjectsEndpoints.GET_PROJECT, projectCode))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .extract().response();
    }

    @Test(dependsOnMethods = "negativeGetProjectByCodeTestWithSC_401")
    public void deleteProject() {
        GetResponseResult projectDel = new ProjectsAdapter().deleteProject(projectCode.toUpperCase());
        System.out.println(projectDel);
    }
}

