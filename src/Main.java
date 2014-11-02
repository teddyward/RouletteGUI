import javafx.application.Application;
import javafx.stage.Stage;
import roulette.Gambler;
import roulette.Game;


/**
 * Plays as many games of roulette until the player runs out of money.
 * 
 * @author Robert C. Duvall
 */
public class Main extends Application {
    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Gambler player = new Gambler("Robert", 1000);
        Game game = new Game(player);
        
        stage.setTitle("Roulette");
        // add our user interface components to Frame and show it
        stage.setScene(game.getScene());
        stage.show();
    }
}
