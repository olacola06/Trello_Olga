import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChangeAvatarTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        if(!app.getUser().loginSuccess()){
            app.getUser().login(User.builder()
                    .email("olamarchen@gmail.com").password("olacola06").build());
            logger.info("Test starts with user details:-> "+"Email: olamarchen@gmail.com, Password: olacola06");
        }
    }
    @Test
    public void changeAvatarSuccess(){
        app.getUser().clickAvatar();
        app.getUser().openProfileAndVisibility();
        app.getUser().openAtlassianProfile();
        Assert.assertTrue(app.getUser().getUrl().contains("id.atlassian.com"));
        Assert.assertTrue(app.getUser().getTitlePage().contains("Atlassian account"));
        //Assert.assertTrue(app.getUser().getEmblemPresentOnPage());

        app.getUser().initChangePhoto();
        app.getUser().uploadPhoto("C:/Users/Olga/Trello_Olga/Trello_Olga/src/test/resources/qa.png");
        Assert.assertTrue(app.getUser().avatarUpdated());
        app.getUser().returnFromAtlassianProfile();
        Assert.assertTrue(app.getUser().getUrl().contains("https://trello.com"));

    }


}
