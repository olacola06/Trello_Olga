import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        if(app.getUser().loginSuccess()){
            app.getUser().logOut();
        }
    }

    @Test
    public void loginSuccess(){
        User user = User.builder().email("olamarchen@gmail.com").password("olacola06").build();
        logger.info("Test starts with user details:-> "+user.getEmail()+", "+user.getPassword());
        app.getUser().initLogin();
        app.getUser().fillRegistForm(user);
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().loginSuccess());
        logger.info("Test passed");
    }
    @Test
    public void loginWrongEmail(){
        User user = User.builder().email("olamarchen@gmailcom").password("olacola06").build();
        app.getUser().initLogin();
        app.getUser().fillRegistForm(user);
        app.getUser().submitLoginError();
        Assert.assertTrue(app.getUser().errorMessage());
        Assert.assertTrue(app.getUser().errorMessageDisplayed("An account with this email address does not exist"));

    }
}
