import domain.CardShuffler;
import domain.card.CardRandomShuffler;
import domain.controller.GameController;
import domain.view.InputView;
import domain.view.OutputView;

public class GameApplication {
    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        final CardShuffler cardShuffler = new CardRandomShuffler();

        GameController gameController = new GameController(inputView, outputView);
        gameController.start(cardShuffler);
    }
}
