package com.trendyol.ui.pages;

import com.trendyol.utils.GenericActions;
import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class ProductPage extends BasePage {
    private final Logger logger = LogManager.getLogger(ProductPage.class);

    @FindBy(id = "add-to-basket")
    private WebElement addToCartButton;

    @Step("Click 'Add To Cart'")
    public ProductPage clickAddToCart() {
        logger.debug("Wait 'Add to Cart button is displayed...");
        GenericActions.waitForVisibility(driver, Duration.ofSeconds(5), addToCartButton);
        logger.debug("Click 'Add to Cart' button");
        addToCartButton.click();
        return this;
    }
}
