package game;


import java.awt.Dimension;

import roulette.Bet;
import roulette.ConsecutiveBet;
import roulette.Gambler;
import roulette.OddEvenBet;
import roulette.RedBlackBet;
import roulette.Wheel;
import util.PopupReader;

/**
 * Plays a game of roulette.
 * 
 * @author Robert C. Duvall
 * @author Teddy Ward
 */
public class GameModel {
    // constants
    public static final Dimension DEFAULT_SIZE = new Dimension(300, 100);

    private Gambler myPlayer;

    private Wheel myWheel;
    private Bet[] myPossibleBets = { new RedBlackBet("Red or Black", 1),
            new OddEvenBet("Odd or Even", 1),
            new ConsecutiveBet("Three in a Row", 11), };

    /**
     * Construct the game.
     */
    public GameModel (Gambler player) {
        myWheel = new Wheel();
        myPlayer = player;
    }

    /**
     * Gives the name of the current player
     */
    public String getPlayerName() {
    	return myPlayer.getName();
    }
    
    /**
     * Gives the current bankroll of the current player
     */
    public String getPlayerBankroll() {
    	return "" + myPlayer.getBankroll();
    }
    
    /**
     * Gives the list of possible bets
     */
    public Bet[] getPossibleBets() {
    	return myPossibleBets;
    }
    
    /**
     * Play a round of this game.
     *
     * For Roulette, this means prompting the player to make a bet, spinning the
     * roulette wheel, and then verifying that the bet is won or lost.
     *
     * @param player one that wants to play a round of the game
     * @return the results of this round of the game
     */
    public String playRound(Bet bet) {
        int amount = PopupReader.promptRange("How much do you want to bet",
                0, myPlayer.getBankroll());
        bet.place();
        
        String resultsOutput = "Spinning ...\n";
        Wheel.SpinResult result = myWheel.spin();
        
        resultsOutput += String.format("Dropped into %s\n", result);

        if (bet.isMade(result)) {
            resultsOutput += "*** Congratulations :) You win ***";
            amount += bet.payout(amount);
        } else {
            resultsOutput += "*** Sorry :( You lose ***";
            amount *= -1;
        }

        myPlayer.updateBankroll(amount);
        return resultsOutput;
    }
}
