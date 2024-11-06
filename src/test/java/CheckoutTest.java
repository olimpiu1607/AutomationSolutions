import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

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


    @Test(description = "Add and remove a product from cart")
    public void addProductsremoveProducts() throws InterruptedException {
        checkoutPage.clickawesomemetalchair();
        Thread.sleep(2000);
        checkoutPage.clickaddToCartButton();
        Thread.sleep(2000);
        checkoutPage.clickpressCartButton();
        Thread.sleep(2000);
        checkoutPage.clickremoveProduct();
        Thread.sleep(2000);
        assertEquals(checkoutPage.getAddSomeProducts().getText(),"How about adding some products in your cart?");
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


//  Acest test adauga acelasi produs in cos, pe care noi l-am pus in checkoutpage.
 //  Iar aici mai avem si diferentele unde nu ne calculeaza TAXELE SI PRETUL SA DEA TOTAL PRICE
    @Test (description = "Calculate the total price for a product")
    public void totalPriceForAProduct(){
//      acesta
        checkoutPage.addProductToCart();
//        asta sus
        System.out.println(checkoutPage.totalPrice());
        ExtentTestNGITestListener.getTest().log(Status.INFO,"The price of the product is : " + checkoutPage.productPrice());
        ExtentTestNGITestListener.getTest().log(Status.INFO,"The tax of the product is : " + checkoutPage.taxPrice());
        double expectedTotal = checkoutPage.productPrice() + checkoutPage.taxPrice();
        ExtentTestNGITestListener.getTest().log(Status.INFO,"The actual total price of the product is : " + checkoutPage.totalPrice());
        ExtentTestNGITestListener.getTest().log(Status.INFO,"The expected total price of the product is : " + expectedTotal);
        assertEquals(checkoutPage.totalPrice(), expectedTotal);
    }

















}

