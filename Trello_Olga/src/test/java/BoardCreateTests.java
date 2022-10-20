import manager.MyDataProvider;
import models.Board;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BoardCreateTests extends TestBase{

    @BeforeTest
    public void preConditions(){
        if(!app.getUser().loginSuccess()){
            app.getUser().login(User.builder().email("olamarchen@gmail.com").password("olacola06").build());
            logger.info("Test starts with user details:-> "+"Email: olamarchen@gmail.com, Password: olacola06");
        }
    }
    @Test
    public void boardCreation(){
        Board board = Board.builder().backgroundColor("Red").build();
        int boardsAmountBefore = app.getBoard().countBoards();
        app.getBoard().initBoardCreation();
        logger.info("Creates board with background color ->>"+board.getBackgroundColor());
        app.getBoard().createBoard(board.getBackgroundColor());

        app.getBoard().returnToMain();
        int boardsAmountAfter = app.getBoard().countBoards();
        Assert.assertNotEquals(boardsAmountBefore-boardsAmountAfter, 1);

    }
    @Test(dataProvider = "backGroundNames", dataProviderClass = MyDataProvider.class)
    public void createBoard(String color){
//        if(app.getBoard().isTen()){
//            app.getBoard().deleteBoard();}
        app.getBoard().initBoardCreation();
        int boardsAmountBefore = app.getBoard().countBoards();
        logger.info("Creates board with background color ->>"+color);
        app.getBoard().createBoard(color);
        int boardsAmountAfter = app.getBoard().countBoards();
        Assert.assertNotEquals(boardsAmountAfter-boardsAmountBefore, 1);
    }
    @Test(dataProvider = "boardName", dataProviderClass = MyDataProvider.class)
    public void createBoard2(Board board){
        if(app.getBoard().isTen()){
            app.getBoard().deleteBoard();}
        app.getBoard().initBoardCreation();
        logger.info("Create board with color->>"+board.getBackgroundColor()+
                " and name->> "+board.getName());
        app.getBoard().createBoard2(board);

    }

}
