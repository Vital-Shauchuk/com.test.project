package pages;

import baseEntities.BasePage;
import core.ReadProperties;
import elements.Button;
import endpoints.UiEndpoints;

import static com.codeborne.selenide.Selenide.open;

public class ProjectSettingsPage extends BasePage {
    public ProjectSettingsPage(boolean openPageByUrl, String endpoint) {
        super(openPageByUrl, endpoint);
    }

    @Override
    protected void openPage(String endpoint) {
        open(ReadProperties.getInstance().getURL() + endpoint);
    }

    public DeleteConfirmationPage clickDeleteProjectButton(String projectCode){
        new Button(" Delete project").click();
        return new DeleteConfirmationPage(false,String.format(UiEndpoints.PROJECT_DELETE_CONFIRMATION,projectCode));
    }
}
