package tests.apiUi;

import adapters.ProjectsAdapter;
import adapters.SuitesAdapter;
import baseEntities.BaseApiTest;
import com.codeborne.selenide.Condition;
import models.projectModels.PostResponseResult;
import models.suitesModels.SuiteGetAllResponseResult;
import org.testng.Assert;
import org.testng.annotations.Test;
import steps.LoginStep;

public class SuiteCreateApiDeleteUiTest extends BaseApiTest {

    private String projectCodeFromAPI;
    private String projectNameFromAPI;
    private String suiteTitleFromApi;


    @Test
    public void createProjectsTestViaAPI() {
        PostResponseResult actProject = new ProjectsAdapter().postCreateProject(expProject);
        projectCodeFromAPI = actProject.getResult().getCode().toUpperCase();
        Assert.assertEquals(
                projectCodeFromAPI,
                expProject.getCode().toUpperCase()
        );
    }

    @Test(dependsOnMethods = "createProjectsTestViaAPI")
    public void getProjectByCodeTestViaApi() {
        PostResponseResult project = new ProjectsAdapter().getProject(projectCodeFromAPI);
        projectNameFromAPI = project.getResult().getTitle();
    }

    @Test(dependsOnMethods = "getProjectByCodeTestViaApi")
    public void postCreateTestSuiteViaApi() {
        new SuitesAdapter().postCreateSuite(expSuite, projectCodeFromAPI);
    }

    @Test(dependsOnMethods = "postCreateTestSuiteViaApi")
    public void getAllSuitesTestViaApi() {
        SuiteGetAllResponseResult suiteList = new SuitesAdapter().getAllSuites(projectCodeFromAPI);
        suiteTitleFromApi = suiteList.getResult().getEntities().get(0).getTitle();
    }

    @Test(dependsOnMethods = "getAllSuitesTestViaApi")
    public void deleteSuiteViaUi() {
        new LoginStep()
                .correctLogin()
                .fillProjectSearchInput(projectNameFromAPI)
                .clickSearchResult(projectNameFromAPI, projectCodeFromAPI)
                .clickDeleteSuiteButton(suiteTitleFromApi, projectCodeFromAPI)
                .clickDeleteSuiteConfirmationButton(projectCodeFromAPI)
                .noSuitesMessage()
                .shouldBe(Condition.visible)
                .shouldHave(Condition.exactText("Looks like you don’t have any any suites and cases yet."));
    }
}
