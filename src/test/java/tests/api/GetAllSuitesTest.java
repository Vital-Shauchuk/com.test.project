package tests.api;

import adapters.ProjectsAdapter;
import adapters.SuitesAdapter;
import baseEntities.BaseApiTest;
import models.projectModels.GetResponseResult;
import models.projectModels.PostResponseResult;
import models.suitesModels.SuiteGetAllResponseResult;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetAllSuitesTest extends BaseApiTest {

    @Test
    public void createProjectsTest() {
        PostResponseResult actProject = new ProjectsAdapter().postCreateProject(expProject);
        Assert.assertEquals(
                actProject.getResult().getCode(),
                expProject.getCode().toUpperCase()
        );
    }

    @Test(dependsOnMethods = "createProjectsTest")
    public void postCreateTestSuite() {
        new SuitesAdapter().postCreateSuite(expSuite, projectCode.toUpperCase());
    }

    @Test(dependsOnMethods = "postCreateTestSuite")
    public void getAllSuitesTest() {
        SuiteGetAllResponseResult suiteList = new SuitesAdapter().getAllSuites(projectCode.toUpperCase());
        Assert.assertEquals(suiteList.getResult().getEntities().get(0).getTitle(), suiteTitle);
    }

    @Test(dependsOnMethods = "getAllSuitesTest")
    public void deleteProject() {
        GetResponseResult projectDel = new ProjectsAdapter().deleteProject(projectCode.toUpperCase());
        System.out.println(projectDel);
    }
}

