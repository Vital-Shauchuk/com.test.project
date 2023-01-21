package pages.workspace;

import baseEntities.BasePage;
import com.codeborne.selenide.SelenideElement;
import core.ReadProperties;
import pages.UpgradeSubscriptionPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MembersPage extends BasePage {

    public MembersPage(boolean openPageByUrl, String endpoint) {
        super(openPageByUrl, endpoint);
    }

    @Override
    protected void openPage(String endpoint) {

        open(ReadProperties.getInstance().getURL() + endpoint);
    }

    private SelenideElement getCustomFieldsMenuItem() {
        return $("a[title='Custom']");
    }

    public UpgradeSubscriptionPage clickCustomFieldsMenuItemUpgrade() {

        getCustomFieldsMenuItem().click();
        return new UpgradeSubscriptionPage();
    }
}
