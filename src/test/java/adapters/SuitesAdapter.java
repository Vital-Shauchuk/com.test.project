package adapters;

import baseEntities.BaseAdapter;
import endpoints.api.SuitesEndpoints;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.projectModels.PostResponseResult;
import models.suitesModels.Suite;
import models.suitesModels.SuiteGetAllResponseResult;
import models.suitesModels.SuiteResponseResult;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class SuitesAdapter extends BaseAdapter {

    public SuiteResponseResult postCreateSuite(Suite suite, String projectCode) {
        Response response = given()
                .body(suite, ObjectMapperType.GSON)
                .when()
                .post(String.format(SuitesEndpoints.CREATE_TESTSUITE, projectCode))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), SuiteResponseResult.class);
    }

    public PostResponseResult deleteSuite(String projectCode, int suiteId) {
        Response response = given()
                .when()
                .delete(String.format(SuitesEndpoints.DELETE_SUITE, projectCode, suiteId))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), PostResponseResult.class);
    }

    public SuiteGetAllResponseResult getAllSuites(String projectCode) {
        Response response = given()
                .when()
                .get(String.format(SuitesEndpoints.GET_ALL_SUITES, projectCode.toUpperCase()))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), SuiteGetAllResponseResult.class);
    }

    public PostResponseResult updateSuite(Suite suite, String projectCode, int suiteId) {
        Response response = given()
                .body(suite, ObjectMapperType.GSON)
                .when()
                .patch(String.format(SuitesEndpoints.UPDATE_SUITE, projectCode, suiteId))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), PostResponseResult.class);
    }
}
