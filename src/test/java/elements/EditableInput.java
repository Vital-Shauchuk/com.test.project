package elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class EditableInput {

    String label;
    String inputField = "//div[@id='undefinedGroup'][label[text()='%s']]//p";

    public EditableInput(String label) {
        this.label = label;
    }

    public void insert(String text) {
        SelenideElement inputField = $x(String.format(this.inputField, label));
        executeJavaScript("arguments[0].innerHTML=arguments[1]", inputField, text);
    }

    public EditableInput clear() {
        SelenideElement inputField = $x(String.format(this.inputField, label));
        executeJavaScript("arguments[0].removeAttribute('data-placeholder')", inputField);
        executeJavaScript("arguments[0].setAttribute('value', arguments[1])", inputField, "");
        return this;
    }
}

