package view;

import com.sun.glass.ui.View;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.SpaceRunnerButton;
import model.SpaceRunnerSubscene;

import java.util.ArrayList;
import java.util.List;

public class ViewManager {

    private static final int HEIGHT = 768;
    private static final int WIDTH = 1024;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private static final int MENU_BUTTONS_START_X = 100;
    private static final int MENU_BUTTONS_START_Y = 200;

    List<SpaceRunnerButton> menuButtons;

    public ViewManager(){
        menuButtons = new ArrayList<SpaceRunnerButton>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane,WIDTH,HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createButtons();
        createBackground();
        createLogo();

        SpaceRunnerSubscene spaceRunnerSubscene = new SpaceRunnerSubscene();

        spaceRunnerSubscene.setLayoutX(200);
        spaceRunnerSubscene.setLayoutY(100);
        mainPane.getChildren().add(spaceRunnerSubscene);
    }
    public Stage getMainStage(){
        return mainStage;
    }
    private void addMenuButton(SpaceRunnerButton spaceRunnerButton){
        spaceRunnerButton.setLayoutX(MENU_BUTTONS_START_X);
        spaceRunnerButton.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 100);

        menuButtons.add(spaceRunnerButton);
        mainPane.getChildren().add(spaceRunnerButton);
    }
    private void createButtons(){
        createStartButton();
        createScoresButton();
        createHelpButton();
        createCreditsButton();
        createExitButton();
    }
    private void createStartButton(){
        SpaceRunnerButton startButton = new SpaceRunnerButton("PLAY");
        addMenuButton(startButton);
    }
    private void createScoresButton(){
        SpaceRunnerButton scoresButton = new SpaceRunnerButton("SCORES");
        addMenuButton(scoresButton);
    }
    private void createHelpButton(){
        SpaceRunnerButton helpButton = new SpaceRunnerButton("HELP");
        addMenuButton(helpButton);
    }
    private void createCreditsButton(){
        SpaceRunnerButton creditsButton = new SpaceRunnerButton("CREDITS");
        addMenuButton(creditsButton);
    }
    private void createExitButton(){
        SpaceRunnerButton exitButton = new SpaceRunnerButton("EXIT");
        addMenuButton(exitButton);
    }
    private void createBackground(){
        Image backgroundImage = new Image("/view/resources/purple.png",256,256,false,true);

        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);

        mainPane.setBackground(new Background(background));
    }
    private void createLogo(){
        final ImageView imageView = new ImageView("/view/resources/logo.png");

        imageView.setLayoutX(400);
        imageView.setLayoutY(50);
        imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                imageView.setEffect(new DropShadow());
            }
        });
        imageView.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                imageView.setEffect(null);
            }
        });
        mainPane.getChildren().add(imageView);
    }
}
