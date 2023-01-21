package steps;

import endpoints.UiEndpoints;
import io.qameta.allure.Step;
import models.TestCase;
import pages.CasePage;
import pages.ProjectPage;

public class CreateCaseStep {

    @Step
    public ProjectPage createCase(String randomProjectCode, TestCase testCase) {
        CasePage casePage = new CasePage(true, String.format(UiEndpoints.CASE_CREATE, randomProjectCode));
        casePage
                .fillCaseForm(testCase)
                .clickSaveButton();
        return new ProjectPage(false, String.format(UiEndpoints.PROJECT, randomProjectCode));
    }

    @Step
    public CasePage createCaseWithTitleOnly(String randomProjectCode, TestCase testCase) {
        CasePage casePage = new CasePage(true, String.format(UiEndpoints.CASE_CREATE, randomProjectCode));
        casePage
                .fillCaseFormWithTitleOnly(testCase)
                .clickSaveButton();
        return new CasePage(false, String.format(UiEndpoints.CASE_CREATE, randomProjectCode));
    }
}
