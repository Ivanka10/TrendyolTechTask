package com.trendyol.ui.helpers;

import com.trendyol.enums.Countries;
import com.trendyol.ui.pages.TopMenuSectionPage;
import com.trendyol.ui.pages.WelcomeToTrendyolPage;

public class LoginHelper {
    private final WelcomeToTrendyolPage welcomeToTrendyolPage = new WelcomeToTrendyolPage();
    private final TopMenuSectionPage topMenuSectionPage = new TopMenuSectionPage();

    public LoginHelper selectCountry(Countries country) {
        welcomeToTrendyolPage
                .clickCountrySelect()
                .selectCountry(country)
                .clickSelectButton();
        return this;
    }

    public void login(String email, String password) {
        topMenuSectionPage
                .clickUserIcon()
                .inputEmail(email)
                .inputPassword(password)
                .clickLogin();
    }
}
