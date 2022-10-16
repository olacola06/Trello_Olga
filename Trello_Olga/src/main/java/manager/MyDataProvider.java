package manager;

import models.Board;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class MyDataProvider{
    @DataProvider
    public Iterator<Object[]> backGroundNames(){
        List<Object[]> colors = new ArrayList();
        colors.add(new Object[]{"Orange"});
        colors.add(new Object[]{"Green"});
        colors.add(new Object[]{"Purple"});
        colors.add(new Object[]{"Blue"});

        return colors.iterator();
    }
    @DataProvider
    public Iterator<Object[]> boardName(){
        Random random = new Random();
        int i = random.nextInt(10);
        List<Object[]> boardName = new ArrayList<>();
        boardName.add(new Object[]{Board.builder().backgroundColor("Orange").name("Orange"+i).build()});
        boardName.add(new Object[]{Board.builder().backgroundColor("Red").name("Red"+i).build()});
        boardName.add(new Object[]{Board.builder().backgroundColor("Green").name("Green"+i).build()});
        boardName.add(new Object[]{Board.builder().backgroundColor("Blue").name("Blue"+i).build()});
        return boardName.iterator();
    }
}
