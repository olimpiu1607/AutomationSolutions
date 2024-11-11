import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;


// Declaring the CheckoutPage class, which extends the BasePage class.
// By extending BasePage, CheckoutPage inherits the WebDriver instance and the PageFactory initialization.
public class CheckoutPage extends BasePage {

    // Declaring a public WebDriverWait variable named 'wait'.
    // WebDriverWait is used for implementing explicit waits during interactions with web elements.
    public WebDriverWait wait;

    // Constructor for the CheckoutPage class that takes a WebDriver object as an argument.
    // This constructor calls the parent class (BasePage) constructor to initialize the WebDriver instance
    // and sets up the PageFactory to initialize the web elements on this page.
    public CheckoutPage(WebDriver driver) {
        // Calling the parent class (BasePage) constructor using 'super' to initialize the WebDriver.
        super(driver);

        // Initializing the WebDriverWait object with a 10-second timeout.
        // This will be used to wait for certain conditions or elements during test execution.
        wait = new WebDriverWait(driver, 10);
    }

    // Locating the search bar element using the @FindBy annotation.
    // @FindBy is a Selenium annotation that helps locate elements on the web page.
    // Here, the element is being located by its 'id' attribute with the value "input-search".
    // Declare the WebElement as private to enforce encapsulation
    // This ensures that 'searchBar' cannot be accessed directly from outside this class
    @FindBy(id = "input-search")
    private WebElement searchBar;

    // A public method to set a value in the search bar.
    // This method interacts with the searchBar element and sends the text "mouse" to it.
    // Public method to interact with the private 'searchBar' element
    // Provides controlled access to the encapsulated WebElement
    public void setSearchBar(String awesome) {
        // Typing the word "mouse" into the search bar.
        searchBar.sendKeys(awesome);
    }

    // Locating the search button element using the @FindBy annotation.
    // The element is being located by its CSS selector, which identifies elements based on their classes.
    // Here, the button has the classes "btn", "btn-light", and "btn-sm".
    @FindBy(css = ".btn.btn-light.btn-sm")
    private WebElement searchButton;

    public void clickSearchButton() {
        searchButton.click();
    }

    @FindBy(linkText = "Refined Frozen Mouse")
    private WebElement frozenMouse;

    public WebElement getFrozenMouse() {
        return frozenMouse;
    }

    @FindBy(linkText = "Awesome Granite Chips")
    private WebElement awesomechipsproduct;

    public void clickawesomechipslink() {
        awesomechipsproduct.click();
    }

    @FindBy(css = ".svg-inline--fa.fa-cart-plus.fa-w-18.fa-3x")
    private WebElement cartIcon;

    public void clickcartIcon() {
        cartIcon.click();
    }

    @FindBy(css = ".svg-inline--fa.fa-shopping-cart.fa-w-18")
    private WebElement shoppingCartIcon;

    public void clickshoppingCartIcon() {
        shoppingCartIcon.click();
    }

    @FindBy(css = ".btn.btn-success")
    private WebElement checkoutCart;

    public void clickcheckoutCart() {
        checkoutCart.click();
    }

    @FindBy(id = "first-name")
    private WebElement firstNameField;

    public void setFirstNameField() {
        firstNameField.sendKeys("Olimpiu");
    }

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    public void setLastNameField() {
        lastNameField.sendKeys("Pop");
    }

    @FindBy(id = "address")
    private WebElement addressField;

    public void setAddressField() {
        addressField.sendKeys("castanului");
    }

    @FindBy(css = ".btn.btn-success")
    private WebElement continueCheckoutButton;

    public void clickcountinueCheckoutButton() {
        continueCheckoutButton.click();
    }

    @FindBy(css = ".btn.btn-success")
    private WebElement completeOrderButton;

    public void clickcompleteOrderButton() {
        completeOrderButton.click();
    }

    @FindBy(css = ".text-muted")
    private WebElement successMessage;

    public WebElement getSuccessMessage() {
        return successMessage;
    }


    @FindBy(linkText = "Awesome Metal Chair")
    private WebElement awesomemetalchair;

    public void clickawesomemetalchair() {
        awesomemetalchair.click();
    }

    @FindBy(css = ".svg-inline--fa.fa-cart-plus.fa-w-18.fa-3x ")
    private WebElement addToCart;

    public void clickaddToCartButton() {
        addToCart.click();
    }

    @FindBy(css = ".svg-inline--fa.fa-shopping-cart.fa-w-18 ")
    private WebElement pressCartButton;

    public void clickpressCartButton() {
        pressCartButton.click();
    }


    @FindBy(css = ".text-center.container")
    private WebElement addSomeProducts;

    public WebElement getAddSomeProducts() {
        return addSomeProducts;
    }


    @FindBy(linkText = "Awesome Soft Shirt")
    private WebElement AwesomeSoftShirt;

    public void clickAwesomeSoftShirt() {
        AwesomeSoftShirt.click();
    }

    @FindBy(css = ".svg-inline--fa.fa-cart-plus.fa-w-18.fa-3x ")
    private WebElement Add2Cart;

    public void clickAdd2Cart() {
        Add2Cart.click();
    }

    @FindBy(css = ".svg-inline--fa.fa-shopping-cart.fa-w-18 ")
    private WebElement clickcartBtn;

    public void clickcartBtn() {
        clickcartBtn.click();
    }

    @FindBy(css = ".svg-inline--fa.fa-plus-circle.fa-w-16 ")
    private WebElement clickplusOne;

    public void clickplusOne() {
        clickplusOne.click();
    }

    @FindBy(css = ".amount-total")
    private WebElement ValidationTotal;

    public WebElement getValidationTotal() {
        return ValidationTotal;
    }


    @FindBy(xpath = "(//td[@class='amount'])[1]")
    private WebElement itemPrice;

    public double productPrice() {
        String amountValue = itemPrice.getText();
        String cleanAmountValue = amountValue.replace("$", "");
        return Double.parseDouble(cleanAmountValue);
    }


    @FindBy(xpath = "(//td[@class='amount'])[2]")
    private WebElement taxPrice;

    public double taxPrice() {
        String taxValue = taxPrice.getText();
        String cleanTaxValue = taxValue.replace("$", "");
        return Double.parseDouble(cleanTaxValue);
    }


    @FindBy(xpath = "(//td[@class='amount'])[3]")
    private WebElement totalPrice;

    public double totalPrice() {
        String totalValue = totalPrice.getText();
        String cleantotalValue = totalValue.replace("$", "");
        return Double.parseDouble(cleantotalValue);
    }


    public void addProductToCart() {
        clickAwesomeSoftShirt();
        clickAdd2Cart();
        clickcartBtn();
    }


    @FindBy(linkText = "Licensed Steel Gloves")
    private WebElement LicensedSteelGloves;

    public void clickLicensedSteelGloves() {
        LicensedSteelGloves.click();
    }

    @FindBy(css = ".svg-inline--fa.fa-heart.fa-w-16.fa-3x")
    private WebElement AddtoWishlist;

    public void clickAddtoWishList() {
        AddtoWishlist.click();
    }

    @FindBy(css = ".svg-inline--fa.fa-shopping-bag.fa-w-14.fa-3x.brand-logo")
    private WebElement backToProductsList;

    public void clickbackToProductsList() {
        backToProductsList.click();
    }

    @FindBy(linkText = "Practical Metal Mouse")
    private WebElement practicalMetalMouse;

    public void clickpracticalMetalMouse() {
        practicalMetalMouse.click();
    }

    @FindBy(css = ".svg-inline--fa.fa-heart.fa-w-16.fa-3x")
    private WebElement AddtoWishList2;

    public void clickAddtoWishList2() {
        AddtoWishList2.click();
    }

    @FindBy(css = ".svg-inline--fa.fa-heart.fa-w-16")
    private WebElement WishListBtn;

    public void clickWishListBtn() {
        WishListBtn.click();
    }


    @FindBy(css = ".btn.btn-link")
    private WebElement brokenHeartIcon;

    public void clickBrokenHeartIcon() {
        brokenHeartIcon.click();
    }


    @FindBy(css = ".svg-inline--fa.fa-undo.fa-w-16")
    private WebElement resetWishlistButton;

    public void clickresetWishlistButton() {
        resetWishlistButton.click();
    }


    @FindBy(css = ".text-muted")
    private WebElement wishlist;

    public WebElement getWishlist() {
        return wishlist;
    }


    @FindBy(css = ".col-md-auto")
    private WebElement cartProduct;

    public WebElement getCartProduct(){
        return cartProduct;
    }

}



