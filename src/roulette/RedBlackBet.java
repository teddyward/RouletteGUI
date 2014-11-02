package roulette;

import java.util.Arrays;
import java.util.Optional;

import org.controlsfx.dialog.Dialogs;

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
        Optional<String> response = Dialogs.create()
                .title("Place a bet!")
                .masthead("Will the ball land in a red or black section?")
                .message("Choose a color:")
                .showChoices(Arrays.asList(Wheel.BLACK, Wheel.RED));
        myChoice = response.get();
    }
}
