package tests.ui;

import baseEntities.BaseTest;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import steps.CreateProjectStep;
import steps.LoginStep;

public class ProjectCreated_AlertTest extends BaseTest {

    Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Test(description = "Alert test")
    @Description("Create project and check alert message")
    public void checkCreateProjectAlert() {

        String randomProjectName = RandomStringUtils.randomAlphanumeric(15);
        String randomProjectCode = RandomStringUtils.randomAlphabetic(6);
        String projectCreatedSuccessMessage = String.format("Project \"%s\" was created successfully!", randomProjectName);

        new LoginStep().correctLogin();

        new CreateProjectStep()
                .createProject(randomProjectName, randomProjectCode)
                .getAlertMessage()
                .shouldHave(Condition.matchText(projectCreatedSuccessMessage));

        LOGGER.error(String.format("Expected Alert text is '%s' ", projectCreatedSuccessMessage));
    }
}
