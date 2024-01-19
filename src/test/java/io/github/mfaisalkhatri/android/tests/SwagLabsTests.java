package io.github.mfaisalkhatri.android.tests;

import io.github.mfaisalkhatri.android.pages.swaglabs.*;
import io.github.mfaisalkhatri.logging.Log;
import org.testng.annotations.DataProvider;
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

    @DataProvider(name = "userData")
    public Object[][] getUserData() {
        return new Object[][]{
                {"John", "Doe", "12345", "standard_user", "secret_sauce"},
        };
    }

    @Test(dataProvider = "userData", priority = 0)
    public void verifyCheckoutFlow(String firstName, String lastName, String postalCode, String username, String password) {

        Log.info("Logging in as the standard user via login page of Swag Labs App");
        loginPage.login(username, password);

        Log.info("Verifying the products page title");
        assertEquals(loginPage.getProductPageTitle(), "PRODUCTS");

        Log.info("Adding the products");
        productListPage.addProducts();

        Log.info("Clicking the cart button");
        productListPage.clickCartButton();

        Log.info("Clicking the checkout button");
        cartPage.clickCheckoutButton();

        Log.info("Entering the first name");
        checkoutInformationPage.enterFirstName(firstName);
        Log.info("Entering the last name");
        checkoutInformationPage.enterLastName(lastName);
        Log.info("Entering the postal code");
        checkoutInformationPage.enterPostalCode(postalCode);
        Log.info("Clicking on the continue button");
        checkoutInformationPage.clickContinueButton();

        Log.info("Clicking on the finish button");
        checkoutOverviewPage.clickFinishButton();

        Log.info("Verifying the thank you text post checkout");
        assertTrue(checkoutCompletePage.isThankYouTextDisplayed());
        Log.info("Verifying the thank you for you order text");
        assertEquals(checkoutCompletePage.getThankYouTextDisplayed(), "THANK YOU FOR YOU ORDER");
        Log.info("Verifying the checkout complete text");
        assertEquals(checkoutCompletePage.getCheckoutCompleteHeaderText(), "CHECKOUT: COMPLETE!");
    }

    @Test(dataProvider = "userData", priority = 1)
    public void verifyFilterOnProducts(String firstName, String lastName, String postalCode, String username, String password) {

        Log.info("Logging in as the standard user via login page of Swag Labs App");
        loginPage.login(username, password);

        Log.info("Verifying the products page title");
        assertEquals(loginPage.getProductPageTitle(), "PRODUCTS");

        Log.info("Filtering the products");
        productListPage.filterProducts();

        Log.info("Tapping on the toggle view button and verifying if the price value is not null");
        assertNotNull(productListPage.tapToggleButton());

        Log.info("Assert the price of first item post toggle");
        assertEquals(productListPage.tapToggleButton(), "$7.99");
    }

    @Test(dataProvider = "userData", priority = 2)
    public void verifyPDPage(String firstName, String lastName, String postalCode, String username, String password) {

        Log.info("Logging in as the standard user via login page of Swag Labs App");
        loginPage.login(username, password);

        Log.info("Verifying the products page title");
        assertEquals(loginPage.getProductPageTitle(), "PRODUCTS");

        Log.info("Filtering the products");
        productListPage.filterProducts();

        Log.info("Checking first item post filter and toggle operations to open PDPage");
        productListPage.clickFirstItemPostFilterAndToggle();

        Log.info("Verify if the back button is displayed on PDPage");
        assertEquals(pdPage.isBackToProductsButtonDisplayed(), true);

        Log.info("Get the name of product on PDPage");
        assertEquals(pdPage.getProductTitleonPDP(), "Sauce Labs Onesie");
    }

}
