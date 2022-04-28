package com.trendyol.ui.pages;

import com.trendyol.enums.Categories;
import com.trendyol.utils.GenericActions;
import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class TopMenuSectionPage extends BasePage {
    private final Logger logger = LogManager.getLogger(TopMenuSectionPage.class);

    @FindBy(xpath = "//div[@class='user']")
    private WebElement userIcon;
    @FindBy(xpath = "//p[contains(@class,'category-name')]")
    private List<WebElement> categories;
    @FindBy(xpath = "//span[@class='subcategory-item']/a")
    private List<WebElement> subcategories;
    @FindBy(xpath = "//p[@class='category-name enable']/../div//img")
    private List<WebElement> subcategoryBannerImages;
    @FindBy(className = "badge-count")
    private WebElement basketProductCountIcon ;


    @Step("Click a User icon")
    public LoginPage clickUserIcon() {
        logger.debug("Wait 'User icon' is displayed...");
        GenericActions.waitForVisibility(driver, Duration.ofSeconds(3), userIcon);
        logger.debug("Click 'User' icon.");
        userIcon.click();
        return new LoginPage();
    }

    @Step("Move to category: {0}")
    public TopMenuSectionPage focusOnCategory(Categories category) {
        logger.debug("Open " + category.getName() + " category.");
        WebElement element = categories.stream().filter(c -> c.getText().equals(category.getName()))
                .findFirst().orElseThrow(() -> new NoSuchElementException(category + " - category not fount"));
        GenericActions.moveToElement(driver, element);
        return this;
    }

    @Step("Check if category banners images are displayed")
    public boolean areCategoryBannerImagesDisplayed() {
        logger.debug("Check if category banner images are displayed.");
        return subcategoryBannerImages.stream().allMatch(i -> GenericActions.isImageLoaded(driver, i));
    }

    @Step("Select a sub category: {0}")
    public TopMenuSectionPage selectSubCategory(String subCategory) {
        logger.debug("Select " + subCategory + " sub category");
        WebElement element = subcategories.stream().filter(c -> c.getText().equals(subCategory))
                .findFirst().orElseThrow(() -> new NoSuchElementException(subCategory + " - subcategory not fount"));
        element.click();
        return this;
    }

    @Step("Get count of products in a cart")
    public int getProductsCountInCart() {
        logger.debug("Get count of products in basket");
        GenericActions.waitForVisibility(driver, Duration.ofSeconds(2), basketProductCountIcon);
        return Integer.parseInt(basketProductCountIcon.getText());
    }

}
