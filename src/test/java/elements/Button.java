package elements;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class Button {

    String label;
    String buttonLocatorBy = "//*[contains(@class, 'btn') and text() ='%s']";

    public Button(String label) {
        this.label = label;
    }

    public void click() {
        $x(String.format(buttonLocatorBy, label))
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.visible)
                .click();
    }

    public Button shouldBe(Condition condition) {
        $x(String.format(buttonLocatorBy, label))
                .shouldBe(condition);
        return this;
    }
}

