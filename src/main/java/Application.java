import controller.BlackjackController;
import domain.card.deckMaker.DeckMaker;
import domain.card.deckMaker.OneRandomDeckMaker;
import domain.participants.Dealer;
import domain.participants.Player;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView(System.in);
        OutputView outputView = new OutputView(System.out);

        DeckMaker deckMaker = new OneRandomDeckMaker();
        BlackjackController controller = new BlackjackController(inputView, outputView);

        controller.start(deckMaker, Player.getDefaultHitStrategy(), Dealer.getDefaultHitStrategy());
    }
}
