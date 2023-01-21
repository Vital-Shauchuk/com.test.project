package adapters;
import baseEntities.BaseAdapter;
import endpoints.api.ProjectsEndpoints;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.projectModels.GetResponseResult;
import models.projectModels.PostResponseResult;
import models.projectModels.Project;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class ProjectsAdapter extends BaseAdapter {

    public PostResponseResult postCreateProject(Project project) {
        Response response = given()
                .body(project, ObjectMapperType.GSON)
                .when()
                .post(ProjectsEndpoints.ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), PostResponseResult.class);
    }

    public GetResponseResult getAllProjects() {
        Response response = given()
                .when()
                .get(ProjectsEndpoints.GET_ALL_PROJECTS)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), GetResponseResult.class);
    }

    public PostResponseResult getProject(String projectCode) {
        Response response = given()
                .when()
                .get(String.format(ProjectsEndpoints.GET_PROJECT,projectCode))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), PostResponseResult.class);
    }

    public GetResponseResult deleteProject(String projectCode) {
        Response response = given()
                .when()
                .delete(String.format(ProjectsEndpoints.DELETE_PROJECT,projectCode))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), GetResponseResult.class);
    }

    public Project updateProjectCode405(Project project, String projectCode) {
        Response response = given()
                .body(project, ObjectMapperType.GSON)
                .when()
                .patch(String.format(ProjectsEndpoints.UPDATE_PROJECT,projectCode))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED)
                .extract().response();
        return project;
    }
}
