import controller.BlackJackController;
import domain.Deck;

import java.util.function.Supplier;

public class Application {

    public static void main(String[] args) {
        BlackJackController blackJackController = new BlackJackController();
        blackJackController.run();
    }
}
