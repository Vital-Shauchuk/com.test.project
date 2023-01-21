package tests.ui;

import baseEntities.BaseTest;
import com.codeborne.selenide.Condition;
import models.TestCase;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import steps.CreateCaseStep;
import steps.CreateProjectStep;
import steps.LoginStep;

public class NegativeBoundaryTest extends BaseTest {

    @Test(description = "Create test case with too long title")
    public void createCaseWithTooLongTitle() {

        String titleErrorMessage = "The title may not be greater than 255 characters.";
        String tooLongTitle = RandomStringUtils.randomAlphabetic(256);
        String randomProjectName = RandomStringUtils.randomAlphanumeric(20);
        String randomProjectCode = RandomStringUtils.randomAlphabetic(6).toUpperCase();

        new LoginStep().correctLogin();

        new CreateProjectStep().createProject(randomProjectName, randomProjectCode);

        new CreateCaseStep()
                .createCaseWithTitleOnly(randomProjectCode, TestCase.builder().title(tooLongTitle).build())
                .getTooLongTitleMessage()
                .shouldBe(Condition.visible)
                .shouldHave(Condition.exactText(titleErrorMessage));
    }
}
