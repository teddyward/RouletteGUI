package roulette;

import java.util.Arrays;
import java.util.Optional;

import org.controlsfx.dialog.Dialogs;

public class OddEvenBet extends Bet {
    private String myChoice;

    public OddEvenBet (String description, int odds) {
        super(description, odds);
        // initialize to default value, so no need to check for null
        myChoice = "";
    }

    @Override
    public boolean isMade (Wheel.SpinResult wheel) {
        return (wheel.getNumber() % 2 == 0 && myChoice.equals("even"))
                || (wheel.getNumber() % 2 == 1 && myChoice.equals("odd"));
    }

    @Override
    public void place () {
        Optional<String> response = Dialogs.create()
                .title("Place a bet!")
                .message("Will the ball land on an even or odd number?")
                .showChoices(Arrays.asList("Odd", "Even"));
        myChoice = response.get();
    }
}
