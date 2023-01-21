package tests.api;

import adapters.ProjectsAdapter;
import adapters.SuitesAdapter;
import baseEntities.BaseApiTest;
import models.projectModels.GetResponseResult;
import models.projectModels.PostResponseResult;
import models.suitesModels.SuiteResponseResult;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteSuiteProjectTest extends BaseApiTest {

    protected int suiteId;

    @Test
    public void createProjectsTest(){
        PostResponseResult actProject = new ProjectsAdapter().postCreateProject(expProject);
        Assert.assertEquals(
                actProject.getResult().getCode(),
                expProject.getCode().toUpperCase()
        );
    }

    @Test(dependsOnMethods = "createProjectsTest")
    public void postCreateTestSuite() {
        SuiteResponseResult suite = new SuitesAdapter().postCreateSuite(expSuite, projectCode.toUpperCase());
        suiteId = suite.getResult().getId();
    }

    @Test(dependsOnMethods = "postCreateTestSuite")
    public void deleteSuite() {
        PostResponseResult suiteDel = new SuitesAdapter().deleteSuite(projectCode.toUpperCase(), suiteId);
        Assert.assertTrue(suiteDel.isStatus());
    }

    @Test(dependsOnMethods = "deleteSuite")
    public void deleteProject() {
        GetResponseResult projectDel = new ProjectsAdapter().deleteProject(projectCode.toUpperCase());
        System.out.println(projectDel);
    }
}
