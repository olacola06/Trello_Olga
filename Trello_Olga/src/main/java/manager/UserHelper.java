package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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

    public boolean errorMessageAppears() {
        try{
            new WebDriverWait(wd,5).until(ExpectedConditions
                    .visibilityOf(wd.findElement(By.xpath("(//p[@class='error-message'])[1]"))));
        }catch (Exception e){
            return false;
        }
        String message = wd.findElement(By.xpath("(//p[@class='error-message'])[1]"))
                .getText();
        System.out.println("Recieved error message:-> "+message.toString());
        return true;
    }
    public void login(User user){
        initLogin();
        fillRegistForm(user);
        submitLogin();
    }

    public void clickAvatar() {
        new WebDriverWait(wd, 15).until(ExpectedConditions.visibilityOf(
                wd.findElement(By.xpath("//button[@data-test-id='header-member-menu-button']"))));
        click(By.xpath("//button[@data-test-id='header-member-menu-button']"));
    }

    public void openProfileAndVisibility() {
        click(By.cssSelector("a[data-test-id='header-member-menu-profile']"));
    }

    public void openAtlassianProfile() {
        click(By.cssSelector("a[href='https://id.atlassian.com/manage-profile']"));
        System.out.println(wd.getWindowHandles());
        List<String> tabs = new ArrayList<>(wd.getWindowHandles());
        wd.switchTo().window(tabs.get(1));
    }

    public String getUrl() {
        return wd.getCurrentUrl();
    }

    public String getTitlePage() {
        return wd.getTitle();
    }

    public boolean getEmblemPresentOnPage() {
        return wd.findElements(By.cssSelector("a[href='/manage-profile']")).size() > 0;
    }

    public void initChangePhoto() {
        click(By.cssSelector("div[data-test-selector='profile-hover-info']"));
        click(By.xpath("(//span[.='Add profile photo'])[4]"));
    }

    public void uploadPhoto(String link) {
//        WebElement el = wd.findElement(By.xpath("//span[.='Upload a photo']"));//WRONG - need Input and Not Span
//        el.sendKeys(link);
        wd.findElement(By.id("image-input")).sendKeys(link);
        click(By.xpath("//span[.='Upload']"));
    }
}
