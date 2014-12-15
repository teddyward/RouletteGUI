package game;


import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import roulette.Bet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * The window through which we can interact with the game
 * @author Teddy Ward
 *
 */
public class GameView {
    // constants
    public static final Dimension DEFAULT_SIZE = new Dimension(300, 100);
    private static final String DEFAULT_NAME = "Roulette";
    
    private Scene myScene;
    private Label myBankrollDisplay;
    private Label myOutput;

    private GameModel myModel;

    /**
     * Construct the game window for the given game.
     * In need of generalization for different types of games.
     */
    public GameView (GameModel model) {
    	myModel = model;
        BorderPane root = new BorderPane();
        VBox game = new VBox();
        addComponentToPane(game, makeStatusBar());
        addComponentToPane(game, makeInteractionBar());
        addComponentToPane(game, makeOutputBar());
        root.setCenter(game);
        myScene = new Scene(root, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
    }
    
    /**
     * gives the scene so that it can be displayed by the application
     */
    public Scene getScene() {
        return myScene;
    }

    /**
     * @return name of the game
     */
    public String getName () {
        return DEFAULT_NAME;
    }
    
    /**
     * Adds a component of the pane (eg: status bar) to the greater scene
     * @param parentPane the scene the component is added to
     * @param component the component to add
     */
    private void addComponentToPane(Pane parentPane, Node component) {
        parentPane.getChildren().add(component);
    }
    
    /**
     * Makes a horizontal panel from a collection of components
     * @param subComponents the components to include in the panel
     * @return the compiled panel
     */
    private Node makeHorizontalPanel(Collection<Node> subComponents) {
        HBox panel = new HBox();
        for(Node n : subComponents) {
            addComponentToPane(panel, n);
        }
        return panel;
    }

    /**
     * Makes a panel to display the status (ie: bankroll) of the player
     */
    private Node makeStatusBar() {
        Label bankrollLabel = new Label("Bankroll: ");
        myBankrollDisplay = new Label(myModel.getPlayerBankroll());
        return makeHorizontalPanel(Arrays.asList(bankrollLabel, myBankrollDisplay));
    }
    
    /**
     * Updates the status bar with more current bankroll information
     */
    private void updateBankrollDisplay() {
        myBankrollDisplay.setText(myModel.getPlayerBankroll());
    }
    
    /**
     * Makes a panel of buttons that the user can use to create different bets
     */
    private Node makeInteractionBar() {
        Collection<Node> betButtons = new ArrayList<Node>();
        for(Bet bet : myModel.getPossibleBets()) {
            betButtons.add(makePlaceBetButton(bet));
        }
        return makeHorizontalPanel(betButtons);
    }
    
    /**
     * Makes a panel that displays the output of different actions the user takes
     * Initialized to display a simple greeting.
     */
    private Node makeOutputBar() {
        myOutput = new Label("Hello " + myModel.getPlayerName() + 
                ", let's play " + getName());
        return makeHorizontalPanel(Arrays.asList(myOutput));
    }
    
    /**
     * Creates a button that will place a bet
     */
    public Button makePlaceBetButton(Bet bet) {
        Button placeBetButton = new Button(bet.toString());
        placeBetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                myOutput.setText(myModel.playRound(bet));
                updateBankrollDisplay();
            }
        });
        return placeBetButton;
    }
    
}
