package vn.edu.fpt.pianotiles.presenter;

import vn.edu.fpt.pianotiles.DBHandler;
import vn.edu.fpt.pianotiles.model.Score;
import vn.edu.fpt.pianotiles.view.CustomToast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GameoverFragmentPresenter {
    DBHandler db;
    int score;
    IGameoverFragment ui;
    boolean isSaved;
    CustomToast toast;

    public GameoverFragmentPresenter(int score, IGameoverFragment ui, DBHandler db, CustomToast toast){
        this.score = score;
        this.ui = ui;
        this.db = db;
        this.isSaved = false;
        this.toast = toast;
    }

    public void loadData(){
        this.ui.setScore(this.score);
    }

    public void saveScore(String playerName){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        Score score = new Score(0, this.score, playerName, strDate);
        this.db.addRecord(score);
        this.toast.setText("Saved!");
        this.toast.show();
        this.isSaved = true;
    }

    public void backToMenu(String playerName){
        if(!this.isSaved){
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String strDate = dateFormat.format(date);
            Score score = new Score(0, this.score, playerName, strDate);
            this.db.addRecord(score);
        }
        this.ui.changePage(0);
    }

    public void playAgain(String playerName){
        if(!this.isSaved){
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String strDate = dateFormat.format(date);
            Score score = new Score(0, this.score, playerName, strDate);
            this.db.addRecord(score);
        }
        this.ui.changePage(1);
    }

    public interface IGameoverFragment{
        void setScore(int score);
        void changePage(int page);
    }
}