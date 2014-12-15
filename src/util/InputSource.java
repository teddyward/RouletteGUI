package util;

/**
 * Abstract class representing the different
 * types of reading that have to be done in order to
 * fully provide the necessary input abilities to a user
 * of roulette.
 * @author Teddy Ward
 *
 */
public abstract class InputSource {

    /**
     * Prompts the user to input an integer value.
     * 
     * @param prompt output to the user before waiting for input
     * @return the value entered, waiting if necessary until one is given
     */
    public abstract int promptInt (String prompt);
    
    /**
     * Prompts the user to input an real value.
     * 
     * @param prompt output to the user before waiting for input
     * @return the value entered, waiting if necessary until one is given
     */
    public abstract double promptDouble (String prompt);
    
    /**
     * Prompts the user to input a word.
     * 
     * @param prompt output to the user before waiting for input
     * @return the value entered, waiting if necessary until one is given
     */
    public abstract String promptString (String prompt);
}
