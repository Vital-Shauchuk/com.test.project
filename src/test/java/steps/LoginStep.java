package steps;

import core.ReadProperties;
import endpoints.UiEndpoints;
import io.qameta.allure.Step;
import pages.LoginPage;
import pages.ProjectsPage;

public class LoginStep {

    @Step
    public ProjectsPage correctLogin() {

        new LoginPage(true, UiEndpoints.LOGIN)
                .setEmail(ReadProperties.getInstance().getUsername())
                .setPassword(ReadProperties.getInstance().getPassword())
                .successLoginBtnClick();
        return new ProjectsPage(false, UiEndpoints.PROJECTS);
    }

    @Step
    public LoginPage incorrectLogin(String incorrectLogin, String incorrectPsw) {

        new LoginPage(true, UiEndpoints.LOGIN)
                .setEmail(incorrectLogin)
                .setPassword(incorrectPsw)
                .successLoginBtnClick();
        return new LoginPage(false, UiEndpoints.LOGIN);
    }
}
