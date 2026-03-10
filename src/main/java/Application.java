import controller.GameController;
import domain.deck.RandomShuffle;
import domain.participant.Dealer;
import domain.deck.Deck;
import domain.GameManager;
import domain.participant.Players;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        GameManager manager = new GameManager(new Deck(new RandomShuffle()), new Dealer(), new Players());

        GameController controller = new GameController(manager, inputView, outputView);
        controller.run();
    }
}
