package manager;

import models.Board;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
}
