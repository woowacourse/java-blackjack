import controller.BlackjackController;
import java.util.List;
import model.card.Card;
import model.deck.Deck;
import model.deck.DeckFactory;
import model.result.Judge;
import view.InputView;
import view.OutputView;

public class BlackjackApplication {
    public static void main(String[] args) {
        List<Card> cards = DeckFactory.getInitializedDeck();
        Deck deck = new Deck(cards);
        Judge judge = new Judge();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        BlackjackController blackjackController = new BlackjackController(deck, judge, inputView, outputView);
        blackjackController.start();

    }
}
