import controller.GameController;
import domain.CardSelector;
import domain.card.RandomUniqueCardSelector;
import view.InputView;
import view.OutputView;

public class GameApplication {
    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        final CardSelector cardSelector = new RandomUniqueCardSelector();

        final GameController gamePrepareController = new GameController(inputView, outputView);
        gamePrepareController.start(cardSelector);
    }
}
