import models.User;
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
    public void createBoard(){
        app.getBoard().initBoardCreation();
        app.getBoard().createBoard("Yellow");
    }
    @Test
    public void createBoard2(){
        app.getBoard().initBoardCreation();
        app.getBoard().createBoard("Blue");
    }


}
