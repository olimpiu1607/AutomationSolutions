import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

// This annotation is used to specify that the ExtentTestNGITestListener class should be used to listen to the test events.
// The listener will generate reports based on the test results using the ExtentReports library.
@Listeners(ExtentTestNGITestListener.class)

// Declaring the CheckoutTest class, which extends the Hooks class.
// By extending Hooks, CheckoutTest inherits the setup and teardown methods for WebDriver.
public class CheckoutTest extends Hooks {

    // Declaring a public variable of type CheckoutPage named 'checkoutPage'.
    // This will be used to interact with the CheckoutPage object during the tests.
    public CheckoutPage checkoutPage;

    public LoginPage loginPage;

    // Declaring a public variable of type WebDriverWait named 'wait'.
    // WebDriverWait is used to explicitly wait for certain conditions or elements during test execution.
    public WebDriverWait wait;

    public SoftAssert softAssert;


    // Method annotated with @BeforeMethod, indicating that it will run before each test method.
    // This method is used to set up the page objects and other necessary components before each test.
    @BeforeMethod
    public void SetupPageObject() {

        // Initializing the checkoutPage object with the current WebDriver instance.
        // This allows the test methods to interact with elements on the checkout page.
        checkoutPage = new CheckoutPage(driver);
        loginPage = new LoginPage(driver);

        // Initializing the WebDriverWait object with the current WebDriver instance and a timeout of 30 seconds.
        // This wait will be used to pause the execution until certain conditions are met or elements are found.
        wait = new WebDriverWait(driver, 30);
        softAssert = new SoftAssert();
    }




    @Test(description = "Tests the search functionality by searching for the keyword 'Awesome'")
    public void searchTest() {
        checkoutPage.setSearchBar("Awesome");
        checkoutPage.clickSearchButton();
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The search engine is looking up for the keyword 'Awesome'");

        List<String> expectedProducts = new ArrayList<>();
        expectedProducts.add("Awesome Granite Chips");
        expectedProducts.add("Awesome Metal Chair");
        expectedProducts.add("Awesome Soft Shirt");

        List<WebElement> productElements = loginPage.getProductElements();

        List<String> actualProducts = new ArrayList<>();
        for (WebElement productElement : productElements){
            actualProducts.add(productElement.getText());
        }

        for (String expectedProduct : expectedProducts){
            softAssert.assertTrue(actualProducts.contains(expectedProduct), "Expected product " + expectedProduct + " not found in the search results");
        }

        for (String actualProduct : actualProducts){
            if (!actualProduct.contains("Awesome")){
                softAssert.fail("Unexpected product found: " + actualProduct);
            }
        }
        softAssert.assertAll();

    }


    @Test(description = "Purchasing a simple product from a guest user")
    public void checkoutTest(){
checkoutPage.clickawesomechipslink();
checkoutPage.clickcartIcon();
checkoutPage.clickshoppingCartIcon();
checkoutPage.clickcheckoutCart();
checkoutPage.setFirstNameField();
checkoutPage.setLastNameField();
checkoutPage.setAddressField();
checkoutPage.clickcountinueCheckoutButton();
checkoutPage.clickcompleteOrderButton();
assertEquals(checkoutPage.getSuccessMessage().getText(), "Order complete");
    }


    @Test(description = "Add product cart")
    public void addProduct(){
        checkoutPage.clickawesomemetalchair();
        checkoutPage.clickaddToCartButton();
        checkoutPage.clickpressCartButton();
        assertEquals(checkoutPage.getCartProduct().getText(),"1");
    }



    @Test(description = "Increase the amount of a product")
    public void increasedAmountTest(){
        checkoutPage.addProductToCart();
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The price of the product is: + " + checkoutPage.productPrice());
        double expectedTotal = checkoutPage.productPrice() * 2;
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The price of the product after quantity increas should be: " + expectedTotal);
        checkoutPage.clickplusOne();
        assertEquals(checkoutPage.productPrice(), expectedTotal);
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The price of the product matches the expected total" + checkoutPage.productPrice() + " = " + expectedTotal);
    }



    @Test(description = "Add product to wishlist")
        public void wishlistProducts() {
        checkoutPage.clickLicensedSteelGloves();
        checkoutPage.clickAddtoWishList();
        checkoutPage.clickbackToProductsList();
        checkoutPage.clickpracticalMetalMouse();
        checkoutPage.clickAddtoWishList2();
        checkoutPage.clickWishListBtn();
        assertEquals(checkoutPage.getWishlist().getText(), "Wishlist");
    }


    @Test(description = "Remove products from wishlist")
    public void removeProductFromWishlist(){
        checkoutPage.clickBrokenHeartIcon();
    }

    @Test(description = "Add product from wishlist to cart")
    public void wishlistToCart(){
        checkoutPage.clickLicensedSteelGloves();
        checkoutPage.clickAddtoWishList();
        checkoutPage.clickWishListBtn();
        assertEquals(checkoutPage.getWishlist().getText(), "Wishlist");
        checkoutPage.clickcartFromWishList();
        checkoutPage.clickcartBtn();
        wait.until(ExpectedConditions.visibilityOf(checkoutPage.getcartProduct1()));
    }


    @Test(description = "Reset Wishlist")
    public void resetWishlist() {
        checkoutPage.clickresetWishlistButton();
    }

    @Test(description = "Negative search test")
    public void negativeSearch(){
        checkoutPage.setSearchBar("Awesome");
        checkoutPage.clickSearchButton();
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The search engine is looking up for the keyword 'Awesome'");
        try {
            if (checkoutPage.getMiscProduct().isDisplayed()) {
                Assert.fail("Element is still present");
            }
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true, "Element 'Awesome' is not found");
        }
    }



}

