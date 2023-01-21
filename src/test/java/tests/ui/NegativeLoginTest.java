package tests.ui;

import baseEntities.BaseTest;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import steps.LoginStep;

public class NegativeLoginTest extends BaseTest {

    @Test
    @Description("Login with incorrect 'Email' field value")
    public void loginWithIncorrectEmailFieldValue() {

        String loginErrorMessage = "These credentials do not match our records.";
        String incorrectLogin = RandomStringUtils.randomAlphanumeric(7) + "@" + RandomStringUtils.randomAlphabetic(5) + ".com";
        String incorrectPSW = RandomStringUtils.randomAlphanumeric(8);

        new LoginStep()
                .incorrectLogin(incorrectLogin, incorrectPSW)
                .getCredentialsDoNotMatchMessage()
                .shouldBe(Condition.visible)
                .shouldHave(Condition.exactText(loginErrorMessage));
    }
}
