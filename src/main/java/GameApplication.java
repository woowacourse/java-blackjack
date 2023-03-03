import controller.GameController;
import domain.CardShuffler;
import domain.card.CardRandomShuffler;
import view.InputView;
import view.OutputView;

public class GameApplication {
    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        final CardShuffler cardShuffler = new CardRandomShuffler();

        GameController gameController = new GameController(inputView, outputView);
        gameController.start(cardShuffler);
    }
}
