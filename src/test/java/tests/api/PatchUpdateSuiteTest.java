package tests.api;

import adapters.ProjectsAdapter;
import adapters.SuitesAdapter;
import baseEntities.BaseApiTest;
import models.projectModels.GetResponseResult;
import models.projectModels.PostResponseResult;
import models.suitesModels.Suite;
import models.suitesModels.SuiteResponseResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PatchUpdateSuiteTest extends BaseApiTest {

    protected int suiteId;
    protected String newSuiteTitle = RandomStringUtils.randomAlphabetic(10);

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
    public void patchUpdateSuite() {
        Suite expSuite = Suite.builder()
                .title(newSuiteTitle)
                .description(suiteDesc)
                .preconditions(suitePreconds )
                .build();
        PostResponseResult suite = new SuitesAdapter().updateSuite(expSuite, projectCode.toUpperCase(), suiteId);
        Assert.assertTrue(suite.isStatus());
    }

    @Test(dependsOnMethods = "patchUpdateSuite")
    public void deleteProject() {
        GetResponseResult projectDel = new ProjectsAdapter().deleteProject(projectCode.toUpperCase());
        System.out.println(projectDel);
    }
}

