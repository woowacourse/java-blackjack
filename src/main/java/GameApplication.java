import controller.GameController;
import view.InputView;
import view.OutputView;

public final class GameApplication {
    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();

        final GameController gameController = new GameController(inputView, outputView);
        gameController.start();
    }
}
