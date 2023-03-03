import controller.GamePrepareController;
import domain.CardShuffler;
import domain.card.CardRandomShuffler;
import view.InputView;
import view.OutputView;

public class GameApplication {
    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        final CardShuffler cardShuffler = new CardRandomShuffler();

        GamePrepareController gamePrepareController = new GamePrepareController(inputView, outputView);
        gamePrepareController.start(cardShuffler);
    }
}
