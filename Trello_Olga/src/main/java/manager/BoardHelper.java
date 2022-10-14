package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
        click(By.cssSelector("div[class='board-tile mod-add']"));
    }

    public void createBoard(String color) {
        Random random = new Random();
        int num = random.nextInt(10);
        String colorLocator = String.format("button[title='%s']",color);
        click(By.cssSelector(colorLocator));
        type(By.cssSelector("input[data-test-id='create-board-title-input']"),color+num);
        click(By.cssSelector("button[data-test-id='create-board-submit-button']"));
    }
}
