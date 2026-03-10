import controller.BlackJackController;
import domain.Deck;

import java.util.function.Supplier;

public class Application {

    public static void main(String[] args) {
        Supplier<Deck> randomDeckSupplier = Deck::new;
        BlackJackController blackJackController = new BlackJackController(randomDeckSupplier);
        blackJackController.run();
    }
}
