package com.ing;



import java.io.IOException;

import javafx.fxml.*;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;

public class MainSceneController implements MapListener, JumpscareListener{

    private int level=1;

    @FXML
    private GridPane grid;
    
    @FXML
    private ImageView jakoo;

    @FXML
    private ImageView joker;
    
    @FXML
    private ImageView moai;

    @FXML
    private ImageView sans;

    @FXML
    private ImageView smiley;

    @FXML
    private ImageView quandale;

    @FXML
    private MediaView media;

    @FXML
    private Text levelCounter;

    private Animatronic animatronic1;
    private Animatronic animatronic2;
    private Animatronic animatronic3;
    private Animatronic animatronic4;
    private Animatronic animatronic5;

    private int maxRowIndex=9;
    private int maxColIndex=9;

    private Map map;
    private Player player;


    @FXML
    private void initialize() {
        grid.addEventFilter(KeyEvent.KEY_PRESSED, arg0 -> {
                moveKey(arg0);
        });
        grid.setFocusTraversable(true);
        grid.requestFocus();

        map = new Map(maxColIndex+1, maxRowIndex+1);
        player = new Player(map, 0, 0, jakoo);
        map.addListener(this);
	    map.addPlayer(player);
        player.addJumpscareListener(this);

        animatronic1 = new Animatronic(map, player, joker);
        animatronic2 = new Animatronic(map, player, moai);
        animatronic3 = new Animatronic(map, player, smiley);
        animatronic4 = new Animatronic(map, player, sans);
        animatronic5 = new Animatronic(map, player, quandale);
        
        map.addAnimatronic(animatronic1);

        

        for (Animatronic animatronic:map.getAnimatronics()) {
            animatronic.startMoving();
        }

    }
    
    @FXML
    private void boom() {
        Media video = new Media(MainSceneController.class.getResource("moai.mp4").toString());
        MediaPlayer player = new MediaPlayer(video);
        media.setMediaPlayer(player);
        player.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                gameOver();
            }
        });
        player.play();
    }

    @FXML
    private void microwave() {
        Media video = new Media(MainSceneController.class.getResource("smiley.mp4").toString());
        MediaPlayer player = new MediaPlayer(video);
        media.setMediaPlayer(player);
        player.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                gameOver();
            }
        });
        player.play();
    }

    @FXML
    private void sans() {
        Media video = new Media(MainSceneController.class.getResource("sans.mp4").toString());
        MediaPlayer player = new MediaPlayer(video);
        media.setMediaPlayer(player);
        player.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                gameOver();
            }
        });
        player.play();
    }

    @FXML
    private void quandale() {
        Media video = new Media(MainSceneController.class.getResource("quandale.mp4").toString());
        MediaPlayer player = new MediaPlayer(video);
        media.setMediaPlayer(player);
        player.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                gameOver();
            }
        });
        player.play();
    }

    @FXML
    private void joker() {
        Media video = new Media(MainSceneController.class.getResource("joker.mp4").toString());
        MediaPlayer player = new MediaPlayer(video);
        media.setMediaPlayer(player);
        player.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                gameOver();
            }
        });
        player.play();
    }

    @FXML
    private void moveKey(KeyEvent event) {
        KeyCode code = event.getCode();
        if(!player.getDead()){
        switch (code) {
            case UP:
            if (player.getPositiony() > 0) {
                map.moveGameObject(player.getPositionx(), player.getPositiony(), player.getPositionx(), player.getPositiony()-1);
            }
            break;
        case DOWN:
            if (player.getPositiony() < maxRowIndex) {
                map.moveGameObject(player.getPositionx(), player.getPositiony(), player.getPositionx(), player.getPositiony()+1);
            }
            break;
        case LEFT:
            if (player.getPositionx() > 0) {
                map.moveGameObject(player.getPositionx(), player.getPositiony(), player.getPositionx()-1, player.getPositiony());
            }
            break;
        case RIGHT:
            if (player.getPositionx() < maxColIndex) {
                map.moveGameObject(player.getPositionx(), player.getPositiony(), player.getPositionx()+1, player.getPositiony());
            }
            break;
        default:
            break;
        }
    }
    }

    
    @FXML
    public void update(GameObject o) {
            GridPane.setConstraints(o.getId(), o.getPositionx()+1, o.getPositiony());
            if (o instanceof Animatronic) {
                if(map.getAnimatronics().get(0).getAggro()!=2000) {
                        return;
                    }
                
                for (Animatronic ami:map.getAnimatronics()) {
                    ami.setAggro(2001);
                }
                level++;

                if(level == 2) {
                    map.addAnimatronic(animatronic2);
                    GridPane.setConstraints(animatronic2.getId(), animatronic2.getPositionx()+1, animatronic2.getPositiony());
                }

                else if(level == 3) {
                    map.addAnimatronic(animatronic3);
                    GridPane.setConstraints(animatronic3.getId(), animatronic3.getPositionx()+1, animatronic3.getPositiony());
                }

                else if(level == 4) {
                    map.addAnimatronic(animatronic4);
                    GridPane.setConstraints(animatronic4.getId(), animatronic4.getPositionx()+1, animatronic4.getPositiony());
                }

                else if(level == 5) {
                    map.addAnimatronic(animatronic5);
                    GridPane.setConstraints(animatronic5.getId(), animatronic5.getPositionx()+1, animatronic5.getPositiony());
                }
                
                else if (level>=6) {
                    player.setDead(true);
                    System.out.println("You won!");
                    for (Animatronic ami:map.getAnimatronics()) {
                        ami.stopMoving();
                    }
                    return;
                }

                System.out.println("You are now on level " + level+"!");
                levelCounter.setText("Level: " + level);
                for (Animatronic l : map.getAnimatronics()) {
                    l.startMoving();
                }
                
            }
            }

    @Override
    public void jumpscare(Animatronic animatronic) {
        if(animatronic.equals(animatronic1)) {
            joker();
        }

        else if(animatronic.equals(animatronic2)) {
            boom();
        }

        else if(animatronic.equals(animatronic3)) {
            microwave();
        }

        else if(animatronic.equals(animatronic4)) {
            sans();
        }
        
        else if(animatronic.equals(animatronic5)) {
            quandale();
        }
    }


    public void gameOver() {
        try {
            App.setRoot("GameOver");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    

    
        
}
        






