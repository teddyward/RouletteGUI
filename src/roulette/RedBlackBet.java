package roulette;

import game.Main;

public class RedBlackBet extends Bet {
    private String myChoice;

    public RedBlackBet (String description, int odds) {
        super(description, odds);
        // no initialization needed for myChoice
    }

    @Override
    public boolean isMade (Wheel.SpinResult wheel) {
        return wheel.getColor().equals(myChoice);
    }

    @Override
    public void place () {
        myChoice = Main.READER.promptOneOf("Please bet", Wheel.BLACK,
                Wheel.RED);
    }
}
