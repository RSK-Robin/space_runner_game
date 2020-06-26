package view;

import com.sun.glass.ui.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class ViewManager {

    private static final int HEIGHT = 768;
    private static final int WIDTH = 1024;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private SpaceRunnerSubscene creditsSubScene;
    private SpaceRunnerSubscene helpSubScene;
    private SpaceRunnerSubscene scoreSubScene;
    private SpaceRunnerSubscene shipChooseSubScene;

    private SpaceRunnerSubscene sceneToHude;

    private static final int MENU_BUTTONS_START_X = 100;
    private static final int MENU_BUTTONS_START_Y = 200;

    List<SpaceRunnerButton> menuButtons;
    List<ShipPicker> shipsList;

    private SHIP choosenShip;

    public ViewManager(){
        menuButtons = new ArrayList<SpaceRunnerButton>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane,WIDTH,HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createButtons();
        createBackground();
        createLogo();
        createSubScenes();
    }
    private void showSubscene(SpaceRunnerSubscene spaceRunnerSubscene){
        if(sceneToHude != null){
            sceneToHude.moveSubScence();
        }
        spaceRunnerSubscene.moveSubScence();
        sceneToHude = spaceRunnerSubscene;
    }
    private void createSubScenes(){
        creditsSubScene = new SpaceRunnerSubscene();
        mainPane.getChildren().add(creditsSubScene);

        helpSubScene = new SpaceRunnerSubscene();
        mainPane.getChildren().add(helpSubScene);

        scoreSubScene = new SpaceRunnerSubscene();
        mainPane.getChildren().add(scoreSubScene);

        createShipChooserSubScene();
    }

    private void createShipChooserSubScene() {
        shipChooseSubScene = new SpaceRunnerSubscene();
        mainPane.getChildren().add(shipChooseSubScene);

        InfoLabel chooseShipLabel = new InfoLabel("CHOOSE YOUR SHIP");
        chooseShipLabel.setLayoutX(110);
        chooseShipLabel.setLayoutY(25);

        shipChooseSubScene.getPane().getChildren().add(chooseShipLabel);
        shipChooseSubScene.getPane().getChildren().add(createShipsToChoose());
        shipChooseSubScene.getPane().getChildren().add(createButtonToStart());
    }
    private HBox createShipsToChoose(){
        HBox box = new HBox();
        box.setSpacing(20);
        shipsList = new ArrayList<ShipPicker>();
        for(SHIP ship: SHIP.values()){
            final ShipPicker shipToPick = new ShipPicker(ship);
            shipsList.add(shipToPick);
            box.getChildren().add(shipToPick);
            shipToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    for(ShipPicker ship: shipsList){
                        ship.setIsCircleChoosen(false);
                    }
                    shipToPick.setIsCircleChoosen(true);
                    choosenShip = shipToPick.getShip();
                }
            });
        }
        box.setLayoutX(300 - (118 * 2));
        box.setLayoutY(100);
        return box;
    }
    private SpaceRunnerButton createButtonToStart(){
        SpaceRunnerButton startButton = new SpaceRunnerButton("START");

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(choosenShip != null){
                    GameViewManager gameViewManager = new GameViewManager();
                    gameViewManager.createNewGame(mainStage, choosenShip);
                }
            }
        });
        startButton.setLayoutX(350);
        startButton.setLayoutY(300);
        return startButton;
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

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showSubscene(shipChooseSubScene);
            }
        });
    }
    private void createScoresButton(){
        SpaceRunnerButton scoresButton = new SpaceRunnerButton("SCORES");
        addMenuButton(scoresButton);

        scoresButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showSubscene(scoreSubScene);
            }
        });
    }
    private void createHelpButton(){
        SpaceRunnerButton helpButton = new SpaceRunnerButton("HELP");
        addMenuButton(helpButton);

        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showSubscene(helpSubScene);
            }
        });
    }
    private void createCreditsButton(){
        SpaceRunnerButton creditsButton = new SpaceRunnerButton("CREDITS");
        addMenuButton(creditsButton);

        creditsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showSubscene(creditsSubScene);
            }
        });
    }
    private void createExitButton(){
        SpaceRunnerButton exitButton = new SpaceRunnerButton("EXIT");
        addMenuButton(exitButton);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.close();
            }
        });
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
