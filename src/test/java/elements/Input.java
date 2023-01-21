package elements;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import groovy.util.logging.Log4j;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

@Log4j
    public class Input {
        String fieldName;
        String inputBy = "//*[contains(text(), '%s')]/following-sibling::input";

        public Input(String fieldName) {
            this.fieldName = fieldName;
        }

        public Input sendValue(String text) {
            if (!$x(String.format(inputBy, fieldName)).shouldBe(visible).is(empty)) {
                clear();
            } else {
                $x(String.format(inputBy, fieldName)).shouldBe(visible).setValue(text);
            }
            return this;
        }

        public Input click() {
            $x(String.format(inputBy, fieldName)).shouldBe(visible).click();
            return this;
        }

        public Input clear() {
            SelenideElement element = $x(String.format(inputBy, fieldName));
            element.click();
            element.clear();
            return this;
        }

        public void shouldHave(String text) {
            $x(String.format(inputBy, fieldName)).shouldHave(Condition.attribute("value", text));
        }

    }