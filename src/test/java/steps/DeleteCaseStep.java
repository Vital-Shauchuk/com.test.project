package steps;

import endpoints.UiEndpoints;
import io.qameta.allure.Step;
import pages.ProjectPage;

public class DeleteCaseStep {

    @Step
    public ProjectPage deleteCase(String randomProjectCode, String randomTestCaseName) {

        ProjectPage projectPage = new ProjectPage(false, String.format(UiEndpoints.PROJECT, randomProjectCode));
        projectPage
                .getTestCaseHeader(randomTestCaseName, randomProjectCode)
                .clickDeleteButton(randomProjectCode)
                .clickDeleteConfirmationButton(randomProjectCode);
        return new ProjectPage(false, String.format(UiEndpoints.PROJECT, randomProjectCode));
    }
}
