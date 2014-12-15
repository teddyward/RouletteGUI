package roulette;

import game.Main;

public class ConsecutiveBet extends Bet {
    private int myStart;

    public ConsecutiveBet (String description, int odds) {
        super(description, odds);
    }

    @Override
    public boolean isMade (Wheel.SpinResult wheel) {
        return (myStart <= wheel.getNumber() && wheel.getNumber() < myStart + 3);
    }

    @Override
    public void place () {
        myStart = Main.READER.promptRange("Enter first of three consecutive numbers", 1, 34);
    }
}
