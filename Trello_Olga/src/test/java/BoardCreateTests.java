import manager.MyDataProvider;
import models.Board;
import models.User;
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
    @Test(dataProvider = "backGroundNames", dataProviderClass = MyDataProvider.class)
    public void createBoard(String color){
        app.getBoard().initBoardCreation();
        logger.info("Creates board with background color ->>"+color);
        app.getBoard().createBoard(color);
    }
    @Test(dataProvider = "boardName", dataProviderClass = MyDataProvider.class)
    public void createBoard2(Board board){
        app.getBoard().initBoardCreation();
        logger.info("Create board with color->>"+board.getBackgroundColor()+
                " and name->> "+board.getName());
        app.getBoard().createBoard2(board);

    }
    @AfterMethod
    public void postCondition(){
        app.getBoard().returnToMain();
    }

}
