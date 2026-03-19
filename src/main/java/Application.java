import controller.GameController;
import domain.card.Deck;
import domain.game.BlackJackGame;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        Deck deck = new Deck();
        BlackJackGame manager = new BlackJackGame(deck);

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        GameController controller = new GameController(manager, inputView, outputView);
        controller.run();
    }
}
