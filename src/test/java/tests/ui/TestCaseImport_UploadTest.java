package tests.ui;

import baseEntities.BaseTest;
import com.codeborne.selenide.Condition;
import endpoints.UiEndpoints;
import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CasePage;
import pages.ProjectPage;
import steps.CreateProjectStep;
import steps.LoginStep;

import java.io.File;

public class TestCaseImport_UploadTest extends BaseTest {

    final String randomProjectName = RandomStringUtils.randomAlphanumeric(15);
    final String randomProjectCode = RandomStringUtils.randomAlphabetic(6).toUpperCase();

    @Test(description = "Upload test: create project")
    @Description("Create project with correct name")
    public void createProjectTest() {

        new LoginStep()
                .correctLogin();
        new CreateProjectStep()
                .createProject(randomProjectName, randomProjectCode);

        Assert.assertEquals(
                new ProjectPage(false, String.format(UiEndpoints.PROJECT, randomProjectCode)).getProjectNameHeader().getText(),
                randomProjectName
        );
    }

    @Test(
            dependsOnMethods = "createProjectTest",
            description = "Upload test: upload test case file"
    )
    @Description("Upload test case file")
    public void uploadTestCaseFileTest() {

        File file = new File("src/test/java/files/testrail.csv");

        CasePage casePage = new CasePage(true, String.format(UiEndpoints.CASE_IMPORT, randomProjectCode));
        casePage
                .getFileUploadField()
                .uploadFile(file);

        boolean isFileUploaded = casePage
                .getFileUploadFieldValue()
                .contains("testrail.csv");

        casePage
                .submitBtnClick()
                .getAlertMessage()
                .shouldHave(Condition.exactText("0 suites and 1 cases were successfully imported!"));

        Assert.assertTrue(isFileUploaded);
    }
}
