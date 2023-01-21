package pages;

import baseEntities.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import core.ReadProperties;
import elements.Button;
import endpoints.UiEndpoints;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ProjectPage extends BasePage {

    public ProjectPage(boolean openPageByUrl, String endpoint) {
        super(openPageByUrl, endpoint);
    }

    @Override
    protected void openPage(String endpoint) {

        open(ReadProperties.getInstance().getURL() + endpoint);
    }

    public SelenideElement getProjectNameHeader() {

        return $("p.header");
    }

    public SelenideElement getAlertMessage() {

        return $(".alert-message");
    }

    public SelenideElement getCreateSuiteButton() {

        return $("#create-suite-button");
    }

    public SelenideElement getSuiteNameInput() {

        return $("#name");
    }

    public SelenideElement getCreateNewSuiteButton() {

        return $(byText("Create new suite"));
    }

    public SelenideElement getSaveSuiteButton() {

        return $("#save-suite-button");
    }

    public String getProjectNameHeaderText() {

        return getProjectNameHeader().getText();
    }

    public ProjectPage clickCreateSuiteButton() {

        getCreateSuiteButton().click();
        return this;
    }

    public ProjectPage clickCreateNewSuiteButton() {

        getCreateNewSuiteButton().click();
        return this;
    }

    public ProjectPage setSuiteName(String suiteName) {

        getSuiteNameInput().sendKeys(suiteName);
        return this;
    }

    public ProjectPage clickSaveSuiteButton() {

        getSaveSuiteButton().click();
        return this;
    }

    public ProjectPage getTestCaseHeader(String testCaseTitle, String projectCode) {
        $x(String.format("//div[@class='cases-container']//div[@class='case-row-title' and text() ='%s']", testCaseTitle)).click();
        return new ProjectPage(false, String.format(UiEndpoints.PROJECT, projectCode));
    }

    public ProjectPage clickDeleteButton(String projectCode) {
        new Button(" Delete").click();
        return new ProjectPage(false, String.format(UiEndpoints.PROJECT, projectCode));
    }

    public ProjectPage clickDeleteConfirmationButton(String projectCode) {
        new Button("Delete").click();
        return new ProjectPage(false, String.format(UiEndpoints.PROJECT, projectCode));
    }

    public ProjectPage clickDeleteSuiteConfirmationButton(String projectCode) {
        $(".btn-danger").click();
        return new ProjectPage(false, String.format(UiEndpoints.PROJECT, projectCode));
    }

    public CasePage clickEditButton(String projectCode) {
        new Button(" Edit").click();
        return new CasePage(false, String.format(UiEndpoints.CASE_EDIT, projectCode));
    }


    public ProjectSettingsPage clickSettingsButton(String projectCode) {
        $x("//li[@class='submenu-item ']/a[@title='Settings']").click();
        return new ProjectSettingsPage(false, String.format(UiEndpoints.PROJECT_SETTINGS, projectCode));
    }

    public SelenideElement alertMessageCaseCreated() {
        return $x("//span[@class='alert-message' and text()='Test case was created successfully!']");
    }

    public SelenideElement alertMessageCaseEdited() {
        return $x("//span[@class='alert-message' and text()='Test case was edited successfully!']");
    }

    public SelenideElement alertMessageCaseDeleted() {
        return $x("//span[@class='alert-message' and contains (text(), 'was successfully deleted')]");
    }


    public ProjectPage clickProjectHeader(String projectCode) {
        $x("//a[@class='defect-title' and text()='%s']").click();
        return this;
    }

    public SelenideElement controlFeedbackMessage() {
        return $x("//div[@class = 'form-control-feedback' and contains(text(),' at least 2 characters.')]");
    }

    private SelenideElement uploadedExample() {
        return $x("//a[contains(text(),'Example')]");
    }

    @Step
    public ProjectPage checkUploadingResults(String projectCode) {
        uploadedExample()
                .shouldBe(Condition.visible)
                .shouldHave(Condition.exactText("Example"));
        return new ProjectPage(false, String.format(UiEndpoints.PROJECT, projectCode));
    }

    private SelenideElement deleteSuiteButton(String projectTitle) {
        return $x(String.format("//a[contains(text(),'%s')]/parent::span/descendant::i[@class='fa fa-trash']", projectTitle));
    }

    public ProjectPage clickDeleteSuiteButton(String projectTitle, String projectCode) {
        deleteSuiteButton(projectTitle)
                .click();
        return new ProjectPage(false, String.format(UiEndpoints.PROJECT, projectCode));
    }

    public SelenideElement noSuitesMessage() {
        return $x("//*[@class='no-project mt-4']");
    }
}
