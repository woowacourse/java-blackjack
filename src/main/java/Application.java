import deck.Deck;
import deck.ShuffledDeckCreator;

public class Application {

    public static void main(String[] args) {
        Deck deck = new Deck(new ShuffledDeckCreator());

        BlackjackGame blackJackGame = new BlackjackGame(deck);
        blackJackGame.run();
    }
}
