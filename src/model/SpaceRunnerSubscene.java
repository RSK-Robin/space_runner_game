package model;

import javafx.scene.Parent;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class SpaceRunnerSubscene extends SubScene {

    private final static String FONT_PATH = "/model/resources/kenvector_future.ttf";
    private final static String BACKGROUND_IMAGE = "/model/resources/yellow_panel.png";

    public SpaceRunnerSubscene() {
        super(new AnchorPane(), 600, 400);
        prefWidth(600);
        prefHeight(400);

        BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE,600,400,false,true),BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);

        AnchorPane root = (AnchorPane) this.getRoot();

        root.setBackground(new Background(image));
    }
}
