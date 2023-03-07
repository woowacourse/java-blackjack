import controller.BlackJackController;
import model.card.Deck;

public class Application {

    public static void main(String[] args) {
        new BlackJackController().init(Deck.create());
    }
}
