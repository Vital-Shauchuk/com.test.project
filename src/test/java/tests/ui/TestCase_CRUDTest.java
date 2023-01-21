package tests.ui;

import baseEntities.BaseTest;
import com.codeborne.selenide.Condition;
import endpoints.UiEndpoints;
import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProjectPage;
import steps.*;

import static com.codeborne.selenide.Condition.visible;

public class TestCase_CRUDTest extends BaseTest {


    final String randomProjectName = RandomStringUtils.randomAlphanumeric(20);
    final String randomProjectCode = RandomStringUtils.randomAlphabetic(6).toUpperCase();

    @Test
    @Description("Create project with correct name")
    public void createProjectTest() {

        new LoginStep().correctLogin();
        new CreateProjectStep().createProject(randomProjectName, randomProjectCode);

        Assert.assertEquals(
                new ProjectPage(false, String.format(UiEndpoints.PROJECT, randomProjectCode)).getProjectNameHeader().getText(),
                randomProjectName
        );
    }

    @Test(dependsOnMethods = "createProjectTest")
    @Description("Create test case")
    public void testCaseCreateTest() {

        new CreateCaseStep()
                .createCase(randomProjectCode, testCaseBuilder)
                .alertMessageCaseCreated()
                .shouldBe(visible)
                .shouldHave(Condition.exactText("Test case was created successfully!"));
    }

    @Test(dependsOnMethods = "testCaseCreateTest")
    @Description("Update test case")
    public void testCaseUpdateTest() {

        new UpdateCaseStep()
                .updateCase(randomProjectCode, testCaseBuilder.getTitle(), testCase2Builder)
                .alertMessageCaseEdited()
                .shouldBe(visible)
                .shouldHave(Condition.exactText("Test case was edited successfully!"));
    }

    @Test(dependsOnMethods = "testCaseUpdateTest")
    @Description("Delete test case")
    public void testCaseDeleteTest() {

        new DeleteCaseStep()
                .deleteCase(randomProjectCode, testCaseBuilder.getTitle())
                .alertMessageCaseDeleted()
                .shouldBe(visible)
                .shouldHave(Condition.text(String.format("Test case ['%s'-'1'] was successfully deleted", randomProjectCode)));
    }

    @Test(dependsOnMethods = "testCaseDeleteTest")
    @Description("Delete project")
    public void projectDeleteTest() {

        new DeleteProjectStep()
                .deleteProject(randomProjectCode)
                .fillProjectSearchInput(randomProjectName)
                .noProjectMessage()
                .shouldBe(Condition.not(visible))
                .shouldHave(Condition.exactText("Looks like you don’t have any projects yet."));
    }
}
