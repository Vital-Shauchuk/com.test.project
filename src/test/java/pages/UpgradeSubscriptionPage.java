package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class UpgradeSubscriptionPage {

    public SelenideElement getUpgradeSubscriptionBtn() {
        return $(".btn.btn-primary");
    }

    public SelenideElement getUpgradeSubscriptionHeader() {
        return $(".modal-content .header");
    }
}
