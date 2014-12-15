package game;
import javafx.application.Application;
import javafx.stage.Stage;
import roulette.Gambler;
import util.ConsoleSource;
import util.InputReader;
import util.PopupSource;


/**
 * Plays as many games of roulette until the player runs out of money.
 * 
 * @author Robert C. Duvall
 * @author Teddy Ward
 */
public class Main extends Application {
	
	/**
	 * This object will read input from the user from an InputSource.  
	 * Just change the parameter to the InputReader constructor
	 * to change the input source.
	 * Currently:
	 * Enter "new ConsoleSource()" to prompt for user input through the console.
	 * Enter "new PopupSource()" to prompt for user input through popup windows.
	 */
	public static final InputReader READER = new InputReader(new PopupSource());
	
    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Gambler player = new Gambler("Robert", 1000);
        GameModel game = new GameModel(player);
        GameView gameView = new GameView(game);
        
        stage.setTitle("Roulette");
        // add our user interface components to Frame and show it
        stage.setScene(gameView.getScene());
        stage.show();
    }
}
