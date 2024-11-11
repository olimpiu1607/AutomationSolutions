import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertEquals;

// This annotation is used to specify that the ExtentTestNGITestListener class should be used to listen to the test events.
// The listener will generate reports based on the test results using the ExtentReports library.
@Listeners(ExtentTestNGITestListener.class)

// Declaring the CheckoutTest class, which extends the Hooks class.
// By extending Hooks, CheckoutTest inherits the setup and teardown methods for WebDriver.
public class LoginTest extends Hooks {

    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);
    // Declaring a public variable of type CheckoutPage named 'checkoutPage'.
    // This will be used to interact with the CheckoutPage object during the tests.
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
        loginPage = new LoginPage(driver);

        // Initializing the WebDriverWait object with the current WebDriver instance and a timeout of 30 seconds.
        // This wait will be used to pause the execution until certain conditions are met or elements are found.
        wait = new WebDriverWait(driver, 30);
        softAssert = new SoftAssert();
    }




    @Test(description = "Login test")
    public void loginTest(){
        loginPage.clickLoginIcon();
        loginPage.setUsername("dino");
        loginPage.setPassword("choochoo");
        loginPage.clickLoginButton();
        assertEquals(loginPage.getuserLoggedIn().getText(), "userLoggedIn");
    }


    @Test (description = "Login Test")
    public void loginWay()throws InterruptedException{
        loginPage.loginUser();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(loginPage.getuserLoggedIn()));
        Thread.sleep(2000);
        loginPage.clickResetButton();
        Thread.sleep(5600);
    }


    @Test(description = "Sorting Test")
    public void sortTest() {
        loginPage.selectOption(loginPage.getSortBar(), "Sort by name (Z to A)");
        List<WebElement> productElements = loginPage.getProductElements();
        List<String> actualProductNames = new ArrayList<>();

        for (WebElement productElement : productElements){
            actualProductNames.add(productElement.getText());
        }

        List<String> expectedProductNames = new ArrayList<>(actualProductNames);
        expectedProductNames.sort(Comparator.reverseOrder());


        Assert.assertEquals(actualProductNames, expectedProductNames, "The products are not sorted in reverse alphabetical order");
    }


    @Test(description = "Sorting Test")
    public void sortTest1() {
        loginPage.selectOption(loginPage.getSortBar(), "Sort by name (A to Z)");
        List<WebElement> productElements = loginPage.getProductElements();
        List<String> actualProductNames = new ArrayList<>();

        for (WebElement productElement : productElements){
            actualProductNames.add(productElement.getText());
        }

        List<String> expectedProductNames = new ArrayList<>(actualProductNames);
        expectedProductNames.sort(Comparator.reverseOrder());
    }


    @Test(description = "Sorting Test")
    public void sortTest2() {
        loginPage.selectOption(loginPage.getSortBar(), "Sort by price (low to high)");
        List<WebElement> productElements = loginPage.getProductElements();
        List<String> actualProductPrice = new ArrayList<>();

        for (WebElement productElement : productElements){
            actualProductPrice.add(productElement.getText());
        }

        List<String> expectedProductPrice = new ArrayList<>(actualProductPrice);
        expectedProductPrice.sort(Comparator.reverseOrder());
    }


    @Test(description = "Sorting Test")
    public void sortTest3() {
        loginPage.selectOption(loginPage.getSortBar(), "Sort by price (high to low)");
        List<WebElement> productElements = loginPage.getProductElements();
        List<String> actualProductNames = new ArrayList<>();

        for (WebElement productElement : productElements){
            actualProductNames.add(productElement.getText());
        }

        List<String> expectedProductNames = new ArrayList<>(actualProductNames);
        expectedProductNames.sort(Comparator.reverseOrder());
    }


    @Test(description = "Log out user")
    public void loggingOut()throws InterruptedException{
        loginPage.loginUser();
        loginPage.clicklogOutButton();
        assertEquals(loginPage.gethelloguest().getText(), "Hello guest!");
    }


    @Test(description = "Incorrect username test")
    public void incorrectUsername(){
        loginPage.clickLoginIcon();
        loginPage.setUsername("dinoh");
        loginPage.setPassword("choochoo");
        loginPage.clickLoginButton();
        assertEquals(loginPage.getError().getText(),"Incorrect username or password!");
        ExtentTestNGITestListener.getTest().log(Status.PASS,"The user is not able to login due to message displayed.");
    }

    @Test(description = "Incorrect password test")
    public void incorrectPassword(){
        loginPage.clickLoginIcon();
        loginPage.setUsername("dino");
        loginPage.setPassword("chiuchiu");
        loginPage.clickLoginButton();
        assertEquals(loginPage.getError().getText(),"Incorrect username or password!");
        ExtentTestNGITestListener.getTest().log(Status.PASS,"The user is not able to login due to message displayed.");
    }


    @Test(description = "Click homepage from a product page")
    public void clickHomePageFromProduct(){
        loginPage.clickAwesomeGraniteChips();
        ExtentTestNGITestListener.getTest().log(Status.INFO,"The product is displayed.");
        loginPage.clickhomePageButton();
        assertEquals(loginPage.getProducts().getText(),"Products");
    }

    @Test(description = "Entering on empty cart")
    public void emptyCart(){
        loginPage.clickShoppingCart();
        assertEquals(loginPage.getEmptyCart().getText(), "How about adding some products in your cart?");
    }

    @Test(description = "Click help button")
    public void helpButton(){
        loginPage.clickHelpButton();
        assertEquals(loginPage.getAfterHelpButton().getText(), "");
    }


















}

