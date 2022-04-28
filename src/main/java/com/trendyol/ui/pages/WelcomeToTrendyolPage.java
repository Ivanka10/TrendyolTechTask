package com.trendyol.ui.pages;

import com.trendyol.enums.Countries;
import com.trendyol.utils.GenericActions;

import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;

public class WelcomeToTrendyolPage extends BasePage {
    private final Logger logger = LogManager.getLogger(WelcomeToTrendyolPage.class);

    @FindBy(className = "country-select")
    private WebElement selectCountry;
    @FindBy(xpath = "//option[@value]")
    private List<WebElement> countriesOption;
    @FindBy(className = "country-actions")
    private WebElement selectButton;

    @Step("Expand a country select")
    public WelcomeToTrendyolPage clickCountrySelect() {
        logger.debug("Wait for country select...");
        GenericActions.waitForVisibility(driver, Duration.ofSeconds(2), selectCountry);
        logger.debug("Expand country select");
        selectCountry.click();
        return this;
    }

    @Step("Select a country: {0} ")
    public WelcomeToTrendyolPage selectCountry(Countries country) {
        logger.debug("Select " + country);
        WebElement element = countriesOption.stream().filter(w -> w.getText().equals(country.getName())).findFirst().get();
        GenericActions.scrollToElement(driver, element);
        element.click();
        return this;
    }

    @Step("Click a Select button")
    public TopMenuSectionPage clickSelectButton() {
        logger.debug("Click 'Select' button");
        selectButton.click();
        return new TopMenuSectionPage();
    }

}
