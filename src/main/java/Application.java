import controller.BlackJackController;
import model.card.Deck;
import model.card.RandomShuffleMaker;

public class Application {

    public static void main(String[] args) {
        new BlackJackController().init(Deck.create(new RandomShuffleMaker()));
    }
}
