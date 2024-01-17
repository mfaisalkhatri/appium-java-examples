package io.github.mfaisalkhatri.android.tests;

import io.github.mfaisalkhatri.android.pages.swaglabs.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SwagLabsTests extends BaseTest {

    LoginPage loginPage = new LoginPage();

    ProductListPage productListPage = new ProductListPage();

    PDPage pdPage = new PDPage();

    CartPage cartPage = new CartPage();

    CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage();

    CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage();

    CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage();

    @Test(priority = 0)
    public void verifyCheckoutFlow() {

        loginPage.login("standard_user", "secret_sauce");

        assertEquals(loginPage.getProductPageTitle(), "PRODUCTS");

        productListPage.addProducts();

        productListPage.clickCartButton();

        cartPage.clickCheckoutButton();

        checkoutInformationPage.enterFirstName("John");
        checkoutInformationPage.enterLastName("Doe");
        checkoutInformationPage.enterPostalCode("12345");
        checkoutInformationPage.clickContinueButton();

        checkoutOverviewPage.clickFinishButton();

        assertTrue(checkoutCompletePage.isThankYouTextDisplayed());
        assertEquals(checkoutCompletePage.getThankYouTextDisplayed(), "THANK YOU FOR YOU ORDER");
        assertEquals(checkoutCompletePage.getCheckoutCompleteHeaderText(), "CHECKOUT: COMPLETE!");
    }

    @Test(priority = 1)
    public void verifyFilterOnProducts() {
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(loginPage.getProductPageTitle(), "PRODUCTS");

        productListPage.filterProducts();

        assertNotNull(productListPage.tapToggleButton());
        assertEquals(productListPage.tapToggleButton(), "$7.99");
    }

    @Test(priority = 2)
    public void verifyPDPage() {
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(loginPage.getProductPageTitle(), "PRODUCTS");

        productListPage.filterProducts();
        productListPage.clickFirstItemPostFilterAndToggle();
        assertEquals(pdPage.isBackToProductsButtonDisplayed(), true);
        assertEquals(pdPage.getProductTitleonPDP(), "Sauce Labs Onesie");
    }

}
