package manager;

import models.Board;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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
        if(isTen()){
            //deleteBoard();
            deleteBoards();
        }
        new WebDriverWait(wd,10).until(ExpectedConditions.visibilityOf
                (wd.findElement(By.cssSelector("div[class='board-tile mod-add']"))));
        click(By.cssSelector("div[class='board-tile mod-add']"));
        pause(3000);
    }

    public void createBoard(String color) {
        Random random = new Random();
        int num = random.nextInt(10);
        String colorLocator = String.format("button[title='%s']",color);
//        new WebDriverWait(wd,10).until(ExpectedConditions.visibilityOf
//                (wd.findElement(By.xpath("//h2[.='Create board']"))));
        String boardName = color+num;
        System.out.println("board name is - "+boardName);
        click(By.cssSelector(colorLocator));
        type(By.cssSelector("input[data-test-id='create-board-title-input']"),boardName);
        new WebDriverWait(wd,15).until(ExpectedConditions
                .visibilityOf(wd.findElement(By.cssSelector("button[data-test-id='create-board-submit-button']")))).click();
        pause(2000);
        Assert.assertTrue(urlContainsColor(boardName));
        returnToMain();
    }

    public boolean urlContainsColor(String name) {
        return getUrl().contains(name.toLowerCase());
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
        new WebDriverWait(wd,20).until(ExpectedConditions.
                visibilityOf(wd.findElement(By.xpath("//button[.='Close board']")))).click();
        //click(By.xpath("//button[.='Close board...']"));
        pause(2000);
        click(By.xpath("//button[.='Close']"));
        pause(2000);
        click(By.xpath("//button[.='Permanently delete board']"));
        click(By.xpath("//button[.='Delete']"));
        try{
        if(wd.findElement(By.cssSelector("div[class='WUvMn55B1GT4IM']")).isDisplayed()){
            click(By.cssSelector("span[aria-label='CloseIcon']"));}
        }catch (Exception e){
            wd.navigate().to("https://trello.com/u/olamarchen/boards");
        }
    }
    public void deleteBoards() {
        List<WebElement> boardsList = wd.findElements(By.cssSelector("a[class$='board-tile']"));
        int boardsAmount = boardsList.size();
        while (boardsAmount > 5) {
            click(By.cssSelector("ul[class='boards-page-board-section-list'] li:first-child"));
            new WebDriverWait(wd, 10).until(ExpectedConditions.visibilityOf(
                    wd.findElement(By.xpath("(//ul[@class='aWNF0hq4282LfA'])[2] /div[2] /li[1]")))).click();
            click(By.xpath("(//button[@aria-label='Board actions menu'])[1]"));
            new WebDriverWait(wd, 20).until(ExpectedConditions.
                    visibilityOf(wd.findElement(By.xpath("//button[.='Close board']")))).click();
            click(By.xpath("//button[.='Close']"));
            click(By.xpath("//button[.='Permanently delete board']"));
            click(By.xpath("//button[.='Delete']"));
            try {
                if (wd.findElement(By.cssSelector("div[class='WUvMn55B1GT4IM']")).isDisplayed()) {
                    click(By.cssSelector("span[aria-label='CloseIcon']"));
                }
            } catch (Exception e) {
                wd.navigate().to("https://trello.com/u/olamarchen/boards");
            }
            boardsAmount--;
        }
    }
    public int countBoards() {
        int amount = wd.findElements(By.cssSelector("a[class$='board-tile']")).size();
        System.out.println("Total amount of boards =  "+amount);
        return amount;
    }
}
