package util;

import java.io.InputStreamReader;
import java.util.Scanner;

public class ConsoleSource extends InputSource {
	
    private Scanner in = new Scanner(new InputStreamReader(System.in));
	
    /**
     * Prompts the user to input an integer value through the console.
     * 
     * @param prompt output to the user before waiting for input
     * @return the value entered, waiting if necessary until one is given
     */
	@Override
	public int promptInt(String prompt) {
        System.out.print(prompt);
        return in.nextInt();
	}
	
    /**
     * Prompts the user to input an real value through the console.
     * 
     * @param prompt output to the user before waiting for input
     * @return the value entered, waiting if necessary until one is given
     */
	@Override
	public double promptDouble(String prompt) {
        System.out.print(prompt);
        return in.nextDouble();
	}

    /**
     * Prompts the user to input a word through the console.
     * 
     * @param prompt output to the user before waiting for input
     * @return the value entered, waiting if necessary until one is given
     */
	@Override
	public String promptString(String prompt) {
        System.out.print(prompt);
        return in.next();
	}

}
