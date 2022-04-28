package com.trendyol.ui.pages;

import com.trendyol.utils.GenericActions;
import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends BasePage {
    private final Logger logger = LogManager.getLogger(SearchResultsPage.class);

    @FindBy(className = "product")
    private List<WebElement> products;
    @FindBy(xpath = "//div[@class='search-result']//img[contains(@class,'image')]")
    private List<WebElement> images;

    @Step("Check if products are present on search result page.")
    public boolean areProductsPresent() {
        logger.debug("Check if products are present on search result page.");
        return products.size() > 0;
    }

    @Step("Check if images are loaded on search result page.")
    public boolean areImagesLoaded() {
        logger.debug("Check if images are loaded on search result page.");
        return images.stream().allMatch(i -> GenericActions.isImageLoaded(driver, i));
    }

    @Step("Open detail page of {0} product.")
    public ProductPage clickProduct(int numberOf) {
        logger.debug("Open detail page of  " + numberOf + " product.");
        products.get(numberOf).click();
        return new ProductPage();
    }
}
