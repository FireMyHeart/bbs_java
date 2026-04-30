package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CompletePage extends BasePage {
    private final By title = By.cssSelector("span.title");
    private final By completeHeader = By.cssSelector("h2[data-test='complete-header']");
    private final By completeText = By.cssSelector("div[data-test='complete-text']");
    private final By backHomeBtn = By.cssSelector("button[id='back-to-products']");
    private final By doneImage = By.cssSelector("img[data-test='pony-express']");
    private final By cartLinkBadge = By.cssSelector("span.shopping_cart_badge");

    public CompletePage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    public String getCompleteHeader() {
        return driver.findElement(completeHeader).getText();
    }

    public String getCompleteText() {
        return driver.findElement(completeText).getText();
    }

    public void clickBackHomeBtn() {
        driver.findElement(backHomeBtn).click();
    }

    public boolean isDoneImageVisible() {
        return driver.findElement(doneImage).isDisplayed();
    }

    public boolean isCartLinkBadgeInvisible() {
        return driver.findElements(cartLinkBadge).isEmpty();
    }
}
