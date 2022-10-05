package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

public class UserHelper extends HelperBase{
    public UserHelper(WebDriver driver) {
        super(driver);
    }

    public void initLogin() {
        click(By.cssSelector("a[href='/login']"));
    }

    public void fillRegistForm(User user) {
        type(By.id("user"),user.getEmail());
        click(By.id("login"));
        pause(1000);
        type(By.id("password"),user.getPassword());
    }

    public boolean loginSuccess() {
        try {
            new WebDriverWait(wd, 5).until(ExpectedConditions.visibilityOf(
                    wd.findElement(By.xpath("//button[@data-test-id='header-member-menu-button']"))));
        }catch (Exception e){
            return false;
        }
        return isElementPresent(By.xpath("//button[@data-test-id='header-member-menu-button']"));
    }

    public void logOut() {
        click(By.xpath("//button[@data-test-id='header-member-menu-button']"));
        click(By.xpath("(//button[@class='R2Zt2qKgQJtkYY'])[3]"));
        click(By.id("logout-submit"));
    }

    public void submitLogin() {
        click(By.id("login-submit"));
    }

    public void submitLoginError() {
        click(By.id("login"));
    }

    public boolean errorMessage() {
        return wd.findElement(By.xpath("(//p[@class='error-message'])[1]"))
                .getText().contains("An account with this email address does not exist");

}
    public boolean errorMessageDisplayed(String text) {
        return new WebDriverWait(wd,5).until(ExpectedConditions
                .textToBePresentInElement(wd.findElement(By.xpath("(//p[@class='error-message'])[1]")),text));
    }
}
