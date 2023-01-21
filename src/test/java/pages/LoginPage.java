package pages;

import baseEntities.BasePage;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import core.ReadProperties;
import endpoints.UiEndpoints;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage extends BasePage {

    //Если в базовом классе определены конструкторы, то в конструкторе производного класса
    //необходимо вызвать один из конструкторов базового класса с помощью ключевого слова super
    public LoginPage(boolean openPageByUrl, String endpoint) {
        super(openPageByUrl, endpoint);
    }

    @Override
    protected void openPage(String endpoint) {
        open(ReadProperties.getInstance().getURL() + endpoint);

        try {
            Alert alert = WebDriverRunner.getWebDriver().switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Alert data: " + alertText);
            alert.accept();
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }

    private SelenideElement getEmailField() {
        return $("#inputEmail");
    }

    private SelenideElement getPasswordField() {
        return $("#inputPassword");
    }

    private SelenideElement getLoginButton() {
        return $("#btnLogin");
    }

    public SelenideElement getCredentialsDoNotMatchMessage() {
        return $(".form-control-feedback");
    }

    // Атомарные методы
    public LoginPage setEmail(String email) {
        getEmailField().sendKeys(email);
        return this;
    }

    public LoginPage setPassword(String password) {
        getPasswordField().sendKeys(password);
        return this;
    }

    public String credentialsDoNotMatch() {
        return getCredentialsDoNotMatchMessage().getText();
    }

    public ProjectsPage successLoginBtnClick() {
        getLoginButton().click();
        return new ProjectsPage(false, UiEndpoints.PROJECTS);
    }
}
