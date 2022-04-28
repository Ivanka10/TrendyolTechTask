package com.trendyol.ui.pages;

import com.trendyol.utils.GenericActions;
import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class LoginPage extends BasePage {
    private final Logger logger = LogManager.getLogger(LoginPage.class);

    @FindBy(className = "q-loader")
    private WebElement loader;
    @FindBy(id = "emaillogin")
    private WebElement emailInput;
    @FindBy(id = "passwordlogin")
    private WebElement passwordInput;
    @FindBy(className = "login-button")
    private WebElement loginButton;

    @Step("Input email {0}")
    public LoginPage inputEmail(String email) {
        logger.debug("Input email.");
        emailInput.sendKeys(email);
        return this;
    }

    @Step("Input password {0}")
    public LoginPage inputPassword(String password) {
        logger.debug("Input password.");
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Click login")
    public void clickLogin() {
        logger.debug("Click login button.");
        loginButton.click();
        GenericActions.waitForInvisibility(driver, Duration.ofSeconds(15), loader);
    }

}
