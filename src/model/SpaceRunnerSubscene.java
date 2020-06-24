package model;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class SpaceRunnerSubscene extends SubScene {

    private final static String FONT_PATH = "/model/resources/kenvector_future.ttf";
    private final static String BACKGROUND_IMAGE = "/model/resources/yellow_panel.png";

    private boolean isHidden;

    public SpaceRunnerSubscene() {
        super(new AnchorPane(), 600, 400);
        prefWidth(600);
        prefHeight(400);

        BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE,600,400,false,true),BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);

        isHidden = true;

        AnchorPane root = (AnchorPane) this.getRoot();

        root.setBackground(new Background(image));

        setLayoutX(1024);
        setLayoutY(180);
    }
    public void moveSubScence(){
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(0.3));
        translateTransition.setNode(this);

        if(isHidden) {
            translateTransition.setToX(-676);
            isHidden = false;
        }
        else {
            translateTransition.setToX(0);
            isHidden = true;
        }

        translateTransition.play();
    }
    public AnchorPane getPane(){
        return (AnchorPane) this.getRoot();
    }
}
