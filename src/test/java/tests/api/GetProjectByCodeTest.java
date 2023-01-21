package tests.api;

import adapters.ProjectsAdapter;
import baseEntities.BaseApiTest;
import models.projectModels.GetResponseResult;
import models.projectModels.PostResponseResult;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetProjectByCodeTest extends BaseApiTest {

    @Test
    public void createProjectsTest() {
        PostResponseResult actProject = new ProjectsAdapter().postCreateProject(expProject);
        Assert.assertEquals(
                actProject.getResult().getCode(),
                expProject.getCode().toUpperCase()
        );
    }

    @Test(dependsOnMethods = "createProjectsTest")
    public void getProjectByCodeTest() {
        PostResponseResult project = new ProjectsAdapter().getProject(projectCode.toUpperCase());
        Assert.assertEquals(
                projectName,
                project.getResult().getTitle()
        );
    }

    @Test(dependsOnMethods = "getProjectByCodeTest")
    public void deleteProject() {
        GetResponseResult projectDel = new ProjectsAdapter().deleteProject(projectCode.toUpperCase());
        System.out.println(projectDel);
    }
}
