import blackjack.domain.Blackjack;
import blackjack.domain.card.Deck;

public class Application {
    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController(new Blackjack(
                Deck.createPack()));
        blackjackController.playBlackJack();
    }
}
