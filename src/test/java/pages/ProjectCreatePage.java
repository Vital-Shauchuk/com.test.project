package pages;

import baseEntities.BasePage;
import com.codeborne.selenide.SelenideElement;
import core.ReadProperties;
import endpoints.UiEndpoints;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProjectCreatePage extends BasePage {

    public ProjectCreatePage(boolean openPageByUrl, String endpoint) {
        super(openPageByUrl, endpoint);
    }

    @Override
    protected void openPage(String endpoint) {
        open(ReadProperties.getInstance().getURL() + endpoint);
    }

    private SelenideElement getProjectNameInput() {

        return $("#inputTitle");
    }

    public SelenideElement getProjectCodeInput() {

        return $("#inputCode");
    }

    private SelenideElement getProjectDescriptionInput() {

        return $("#inputDescription");
    }

    private SelenideElement getPrivateAccessRadioButton() {

        return $("#private-access-type");
    }

    private SelenideElement getPublicAccessRadioButton() {

        return $("#public-access-type");
    }

    private SelenideElement getCreateProjectButton() {

        return $("button.btn-primary");
    }

    public SelenideElement getProjectNameErrorMessage() {

        return $(".form-control-feedback");
    }

    public ProjectCreatePage setProjectName(String projectName){

        getProjectNameInput().sendKeys(projectName);
        return this;
    }

    public ProjectCreatePage setProjectCode(String projectCode){

        getProjectCodeInput().clear();
        getProjectCodeInput().sendKeys(projectCode);
        return this;
    }

    public ProjectCreatePage setProjectDescription(String projectDescription){

        getProjectDescriptionInput().sendKeys(projectDescription);
        return this;
    }

    public ProjectCreatePage setProjectPrivateAccessType(){

        getPrivateAccessRadioButton().click();
        return this;
    }

    public ProjectCreatePage setProjectPublicAccessType(){

        getPublicAccessRadioButton().click();
        return this;
    }

    public ProjectCreatePage clickCreateProjectBtn(){

        getCreateProjectButton().click();
        return this;
    }

    public ProjectPage clickCreateProjectSuccessBtn(){

        getCreateProjectButton().click();
        return new ProjectPage(false, UiEndpoints.PROJECT);
    }

    public String getProjectNameErrorMessageText(){

        return getCreateProjectButton().getText();
    }
}
