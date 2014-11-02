package roulette;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.controlsfx.dialog.Dialogs;

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
 * Plays a game of roulette.
 * 
 * @author Robert C. Duvall
 */
public class Game {
    // constants
    public static final Dimension DEFAULT_SIZE = new Dimension(300, 100);
    private static final String DEFAULT_NAME = "Roulette";
    
    private Scene myScene;
    private Label myBankrollDisplay;
    private Label myOutput;
    private Gambler myPlayer;

    private Wheel myWheel;
    private Bet[] myPossibleBets = { new RedBlackBet("Red or Black", 1),
            new OddEvenBet("Odd or Even", 1),
            new ConsecutiveBet("Three in a Row", 11), };

    /**
     * Construct the game.
     */
    public Game (Gambler player) {
        myWheel = new Wheel();
        myPlayer = player;
        BorderPane root = new BorderPane();
        VBox game = new VBox();
        addComponentToPane(game, makeStatusBar());
        addComponentToPane(game, makeInteractionBar());
        addComponentToPane(game, makeOutputBar());
        root.setCenter(game);
        myScene = new Scene(root, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
    }
    
    public Scene getScene() {
        return myScene;
    }

    /**
     * @return name of the game
     */
    public String getName () {
        return DEFAULT_NAME;
    }
    
    private void addComponentToPane(Pane parentPane, Node component) {
        parentPane.getChildren().add(component);
    }
    
    private Node makeHorizontalPanel(Collection<Node> subComponents) {
        HBox panel = new HBox();
        for(Node n : subComponents) {
            addComponentToPane(panel, n);
        }
        return panel;
    }

    private Node makeStatusBar() {
        Label bankrollLabel = new Label("Bankroll: ");
        myBankrollDisplay = new Label("" + myPlayer.getBankroll());
        return makeHorizontalPanel(Arrays.asList(bankrollLabel, myBankrollDisplay));
    }
    
    private Node makeInteractionBar() {
        Collection<Node> betButtons = new ArrayList<Node>();
        for(Bet bet : myPossibleBets) {
            betButtons.add(makePlaceBetButton(bet));
        }
        return makeHorizontalPanel(betButtons);
    }
    
    private Node makeOutputBar() {
        myOutput = new Label("Hello " + myPlayer.getName() + 
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
                playRound(bet);
            }
        });
        return placeBetButton;
    }
    
    private void updateBankrollDisplay() {
        myBankrollDisplay.setText("" + myPlayer.getBankroll());
    }
    
    /**
     * Play a round of this game.
     *
     * For Roulette, this means prompting the player to make a bet, spinning the
     * roulette wheel, and then verifying that the bet is won or lost.
     *
     * @param player one that wants to play a round of the game
     */
    private void playRound(Bet bet) {
        Optional<String> response = Dialogs.create()
                .title("Place a bet!")
                .message("Enter the amount you'd like to bet")
                .showTextInput("");
        int amount = Integer.parseInt(response.get());
        bet.place();
        
        myOutput.setText("Spinning ...");
        Wheel.SpinResult result = myWheel.spin();
        
        String resultsOutput = String.format("Dropped into %s\n", result);

        if (bet.isMade(result)) {
            resultsOutput += "*** Congratulations :) You win ***";
            amount += bet.payout(amount);
        } else {
            resultsOutput += "*** Sorry :( You lose ***";
            amount *= -1;
        }
        
        myOutput.setText(resultsOutput);
        myPlayer.updateBankroll(amount);
        updateBankrollDisplay();
    }
}
