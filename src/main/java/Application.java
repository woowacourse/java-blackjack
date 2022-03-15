import blackjack.controller.BlackJackController;
import blackjack.domain.card.CardDistributor;
import blackjack.domain.card.DeckGenerator;

public class Application {
    public static void main(String[] args) {
        DeckGenerator deckGenerator = new DeckGenerator();
        CardDistributor cardDistributor = new CardDistributor(deckGenerator);
        BlackJackController blackJackController = new BlackJackController(cardDistributor);
        blackJackController.run();
    }
}
