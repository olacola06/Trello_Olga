package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BoardHelper extends HelperBase{

    public BoardHelper (WebDriver driver){
        super(driver);
    }

    public void initBoardCreation() {
        if (isElementPresent(By.cssSelector("div[class='board-tile mod-add']"))) {
            click(By.cssSelector("div[class='board-tile mod-add']"));
        }
        else click(By.cssSelector(""));
    }

    public void createBoard(String color) {
        type(By.cssSelector("input[data-test-id='create-board-title-input']"),color);
        click(By.cssSelector("button[data-test-id='create-board-submit-button']"));
    }
}
