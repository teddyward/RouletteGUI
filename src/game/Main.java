package game;
import javafx.application.Application;
import javafx.stage.Stage;
import roulette.Gambler;


/**
 * Plays as many games of roulette until the player runs out of money.
 * 
 * @author Robert C. Duvall
 * @author Teddy Ward
 */
public class Main extends Application {
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
