package pages;

import baseEntities.BasePage;
import core.ReadProperties;
import elements.Button;
import endpoints.UiEndpoints;

import static com.codeborne.selenide.Selenide.open;

public class DeleteConfirmationPage extends BasePage {

    public DeleteConfirmationPage(boolean openPageByUrl, String endpoint) {
        super(openPageByUrl, endpoint);
    }

    @Override
    protected void openPage(String endpoint) {
        open(ReadProperties.getInstance().getURL() + endpoint);
    }

    public ProjectsPage clickDeleteConfirmationButton(String projectCode) {
        new Button(" Delete project").click();
        return new ProjectsPage(false, String.format(UiEndpoints.PROJECTS));
    }
}
