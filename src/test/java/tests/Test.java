package tests;

import static com.codeborne.selenide.Selenide.$x;

public class Test {

    public void click() {
        $x("//*[contains(@class, 'btn') and text() ='%s']");
    }
}
