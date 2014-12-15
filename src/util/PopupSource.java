package util;

import org.controlsfx.dialog.Dialogs;

/**
 * Provides variety of methods to simplify getting user input from popups.
 * 
 * @author Teddy Ward
 */
public class PopupSource extends InputSource {
    private static String BET_TITLE = "Place a bet!";

    /**
     * Prompts the user to input an integer value through a popup.
     * 
     * @param prompt output to the user before waiting for input
     * @return the value entered, waiting if necessary until one is given
     */
    @Override
    public int promptInt (String prompt)
    {
        try {
            return Integer.parseInt(promptString(prompt)); 
        }
        catch (NumberFormatException nfe) {
            return -1;
        }
    }

    /**
     * Prompts the user to input an real value through a popup.
     * 
     * @param prompt output to the user before waiting for input
     * @return the value entered, waiting if necessary until one is given
     */
    @Override
    public double promptDouble (String prompt)
    {
        try {
            return Double.parseDouble(promptString(prompt)); 
        }
        catch (NumberFormatException nfe) {
            return -1;
        }
    }

    /**
     * Prompts the user to input a word through a popup.
     * 
     * @param prompt output to the user before waiting for input
     * @return the value entered, waiting if necessary until one is given
     */
    @Override
    public String promptString (String prompt) {
        return Dialogs.create()
                .title(BET_TITLE)
                .message(prompt)
                .showTextInput("")
                .get();
    }
}
