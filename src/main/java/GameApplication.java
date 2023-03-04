import controller.GameController;
import view.InputView;
import view.OutputView;

public class GameApplication {
    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();

        final GameController gamePrepareController = new GameController(inputView, outputView);
        gamePrepareController.start();
    }
}
