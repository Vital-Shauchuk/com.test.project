package tests.api;

import adapters.ProjectsAdapter;
import baseEntities.BaseApiTest;
import endpoints.api.ProjectsEndpoints;
import io.restassured.mapper.ObjectMapperType;
import models.projectModels.GetResponseResult;
import models.projectModels.PostResponseResult;
import models.projectModels.Project;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class NegativePatchStatusCode_405Test extends BaseApiTest {

    @Test
    public void createProjectsTest() {
        PostResponseResult actProject = new ProjectsAdapter().postCreateProject(expProject);
        Assert.assertEquals(
                actProject.getResult().getCode(),
                expProject.getCode().toUpperCase()
        );
    }

    @Test(dependsOnMethods = "createProjectsTest")
    public void negativePatchUpdateProjectWithSC_405() {
        Project project = Project.builder()
                .title(projectName + "qwerty")
                .code(projectCode)
                .description("lorem ipsum")
                .build();

        given()
                .body(project, ObjectMapperType.GSON)
                .when()
                .patch(String.format(ProjectsEndpoints.UPDATE_PROJECT, projectCode))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED)
                .extract().response();
    }

    @Test(dependsOnMethods = "negativePatchUpdateProjectWithSC_405")
    public void deleteProject() {
        GetResponseResult projectDel = new ProjectsAdapter().deleteProject(projectCode.toUpperCase());
        System.out.println(projectDel);

    }
}
