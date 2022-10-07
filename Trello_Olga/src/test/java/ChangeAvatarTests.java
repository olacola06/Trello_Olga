import models.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChangeAvatarTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        if(!app.getUser().loginSuccess()){
            app.getUser().initLogin();
            app.getUser().fillRegistForm(User.builder()
                    .email("olamarchen@gmail.com").password("olacola06").build());
            app.getUser().submitLogin();
        }
    }
    @Test
    public void changeAvatar(){
        app.getUser().initChangeAvatar();
        app.getUser().uploadNewLogo();


    }


}
