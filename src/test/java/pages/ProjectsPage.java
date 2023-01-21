package pages;

import baseEntities.BasePage;
import com.codeborne.selenide.SelenideElement;
import core.ReadProperties;
import endpoints.UiEndpoints;

import static com.codeborne.selenide.Selenide.*;

public class ProjectsPage extends BasePage {

    public ProjectsPage(boolean openPageByUrl, String endpoint) {
        super(openPageByUrl, endpoint);
    }

    @Override
    protected void openPage(String endpoint) {
        open(ReadProperties.getInstance().getURL() + endpoint);
    }

    public SelenideElement getUserMenuIcon() {

        return $("#user-menu");
    }

    private SelenideElement getCreateProjectButton() {

        return $("#createButton");
    }

    public ProjectCreatePage createProjectButtonClick() {

        getCreateProjectButton().click();
        return new ProjectCreatePage(false, UiEndpoints.CREATE_PROJECT);
    }

    public ProjectsPage fillProjectSearchInput(String projectName){
        projectSearchField().sendKeys(projectName);
        return new ProjectsPage(false, UiEndpoints.PROJECTS);
    }

    public SelenideElement noProjectMessage() {
        return $x("//*[@class='no-project mt-4']");
    }

    public SelenideElement projectSearchField(){
        return $x("//input[@placeholder='Search for projects']");
    }

    public ProjectPage clickSearchResult(String projectTitle, String projectCode){
        $x(String.format("//a[contains(text(),'%s')]",projectTitle)).click();
        return new ProjectPage(false,String.format(UiEndpoints.PROJECT,projectCode));
    }
}
