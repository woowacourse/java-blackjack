import domain.deck.CardDeck;
import domain.deck.StandardDeck;

public class Application {
    public static void main(String[] args) {
        CardDeck cardDeck = new StandardDeck();
        BlackJack blackJack = new BlackJack(cardDeck);

        blackJack.start();
    }
}
