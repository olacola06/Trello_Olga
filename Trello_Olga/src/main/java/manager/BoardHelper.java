package manager;

import models.Board;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class BoardHelper extends HelperBase{

    public BoardHelper (WebDriver driver){
        super(driver);
    }

    public void initBoardCreation() {
//        if (isElementPresent(By.cssSelector("div[class='board-tile mod-add']"))) {
//            click(By.cssSelector("div[class='board-tile mod-add']"));
//        }
//        else click(By.cssSelector(""));
        new WebDriverWait(wd,10).until(ExpectedConditions.visibilityOf
                (wd.findElement(By.cssSelector("div[class='board-tile mod-add']"))));
        click(By.cssSelector("div[class='board-tile mod-add']"));
        pause(2000);
    }

    public void createBoard(String color) {
        Random random = new Random();
        int num = random.nextInt(10);
        String colorLocator = String.format("button[title='%s']",color);
        new WebDriverWait(wd,10).until(ExpectedConditions.visibilityOf
                (wd.findElement(By.xpath("//div[.='Create board']"))));
        click(By.cssSelector(colorLocator));
        type(By.cssSelector("input[data-test-id='create-board-title-input']"),color+num);
        click(By.cssSelector("button[data-test-id='create-board-submit-button']"));
        pause(1000);
        returnToMain();
    }
    public void createBoard2(Board board) {
        String colorLocator = String.format("button[title='%s']",board.getBackgroundColor());
        click(By.cssSelector(colorLocator));
        type(By.cssSelector("input[data-test-id='create-board-title-input']"), board.getName());
        click(By.cssSelector("button[data-test-id='create-board-submit-button']"));
        pause(1000);
        returnToMain();
    }

    public void returnToMain() {
        pause(2);
        click(By.xpath("//p[.='QA_12_Olga']"));
    }

    public boolean isTen() {
        List<WebElement> boardsList = wd.findElements(By.cssSelector("a[class$='board-tile']"));
        int amount = boardsList.size();
        System.out.println("Total boards created = "+amount);
        return amount == 10;
    }

    public void deleteBoard() {
        click(By.cssSelector("ul[class='boards-page-board-section-list'] li:first-child"));
        new WebDriverWait(wd,10).until(ExpectedConditions.visibilityOf(
                wd.findElement(By.xpath("(//ul[@class='aWNF0hq4282LfA'])[2] /div[2] /li[1]")))).click();
        click(By.xpath("(//button[@aria-label='Board actions menu'])[1]"));
        pause(1000);
        click(By.xpath("//button[.='Close board...']"));
        pause(1000);
        click(By.xpath("//button[.='Close']"));
        pause(1000);
        click(By.xpath("//button[.='Permanently delete board']"));
        click(By.xpath("//button[.='Delete']"));
        try{
        if(wd.findElement(By.cssSelector("div[class='WUvMn55B1GT4IM']")).isDisplayed()){
            click(By.cssSelector("span[aria-label='CloseIcon']"));}
        }catch (Exception e){
            wd.navigate().to("https://trello.com/u/olamarchen/boards");
        }
    }
}
