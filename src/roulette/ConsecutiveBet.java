package roulette;

import java.util.Optional;

import org.controlsfx.dialog.Dialogs;

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
        Optional<String> response = Dialogs.create()
                .title("Place a bet!")
                .masthead("Will the ball land in one of a sequence of numbers?")
                .message("Enter the start of a three number sequence you'd like to bet on:")
                .showTextInput("");
        myStart = Integer.parseInt(response.get());
    }
}
