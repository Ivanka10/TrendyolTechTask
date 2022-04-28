package com.trendyol;

import com.trendyol.driver.DriverManager;
import com.trendyol.enums.Categories;
import com.trendyol.enums.Countries;
import com.trendyol.ui.helpers.LoginHelper;
import com.trendyol.ui.pages.ProductPage;
import com.trendyol.ui.pages.SearchResultsPage;
import com.trendyol.ui.pages.TopMenuSectionPage;
import com.trendyol.utils.PropertyReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import report.CustomListener;

import static com.trendyol.utils.Constants.EMAIL;
import static com.trendyol.utils.Constants.PASSWORD;

@Listeners({ CustomListener.class })
public class SmokeTest {

    private final PropertyReader propertyReader = new PropertyReader();
    private final LoginHelper loginHelper = new LoginHelper();
    private final TopMenuSectionPage topMenuSectionPage = new TopMenuSectionPage();
    private final SearchResultsPage searchResultsPage = new SearchResultsPage();
    private final ProductPage productPage = new ProductPage();


    @Test(description = "Verify loading images and adding product to the cart.")
    public void addProductToCartTest() {
        SoftAssertions softAssert = new SoftAssertions();

        loginHelper
                .selectCountry(Countries.UNITED_KINGDOM)
                .login(propertyReader.propertyFile(EMAIL), propertyReader.propertyFile(PASSWORD));
        topMenuSectionPage.focusOnCategory(Categories.NEW_IN);

        softAssert.assertThat(topMenuSectionPage.areCategoryBannerImagesDisplayed())
                .as("Category images should be displayed").isTrue();

        topMenuSectionPage.selectSubCategory(Categories.NEW_IN.getSubCategories().get(0));

        softAssert.assertThat(searchResultsPage.areProductsPresent())
                .as("Products should be present").isTrue();
        softAssert.assertThat(searchResultsPage.areImagesLoaded())
                .as("Products images should be displayed").isTrue();

        searchResultsPage.clickProduct(1);
        int beforeCountOfProducts = topMenuSectionPage.getProductsCountInCart();
        productPage.clickAddToCart();
        int actualCountOfProducts = topMenuSectionPage.getProductsCountInCart();

        softAssert.assertThat(actualCountOfProducts)
                .as("Wrong number of products count").isEqualTo(beforeCountOfProducts + 1);
        softAssert.assertAll();

    }

    @AfterMethod
    public void tearDown() {
        DriverManager.tearDown();
    }
}
