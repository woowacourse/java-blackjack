import controller.GameController;
import domain.Deck;

public class Application {
    
    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.run(new Deck());
    }
}
