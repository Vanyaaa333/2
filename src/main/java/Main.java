import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Main {

    protected static WebDriver driver;
    protected static WebDriverWait explicitlyWait;
    private static final By ADD_PRODUCT_BUTTON_LOCATOR = By.xpath("//button[contains(.,'Добавить в корзину')]");
    private static final By CART_LOCATOR = By.xpath("//a[contains(.,'Корзина')]");
    private static final By INCREASING_PRODUCT_NUMB_LOCATOR = By.xpath("//button[contains(@class,'count__plus')]");
    private static final By ACTUAL_PRODUCT_ADDED_NUMB_LOCATOR = By.xpath("//input[contains(@class,'in_tb')]");
    private static final String EXPECTED_PRODUCT_ADDED_NUMB_LOCATOR = "2";

    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "start-maximized"
        );
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        explicitlyWait = new WebDriverWait(driver, 5);

        driver.get("https://www.wildberries.ru/catalog/11200331/detail.aspx");
        driver.findElement(ADD_PRODUCT_BUTTON_LOCATOR).click();
        driver.findElement(CART_LOCATOR).click();
        explicitlyWait.until(ExpectedConditions.visibilityOfElementLocated(INCREASING_PRODUCT_NUMB_LOCATOR));
        driver.findElement(INCREASING_PRODUCT_NUMB_LOCATOR).click();
        Assert.assertEquals(driver.findElement(ACTUAL_PRODUCT_ADDED_NUMB_LOCATOR).getAttribute("maxlength"),
                EXPECTED_PRODUCT_ADDED_NUMB_LOCATOR,"Added product count isn't right");
        driver.quit();
    }


}
