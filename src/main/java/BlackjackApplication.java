import controller.BlackjackController;
import java.util.List;
import model.card.Card;
import model.deck.Deck;
import model.deck.DeckFactory;
import view.InputView;
import view.OutputView;

public class BlackjackApplication {
    public static void main(String[] args) {
        List<Card> cards = DeckFactory.initializeDeck();
        Deck deck = new Deck(cards);
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        BlackjackController blackjackController = new BlackjackController(deck, inputView, outputView);
        blackjackController.start();
    }
}
