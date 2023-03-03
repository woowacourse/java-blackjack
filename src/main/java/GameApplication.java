import domain.controller.GameController;
import domain.view.InputView;

public class GameApplication {
    public static void main(String[] args) {
        final InputView inputView = new InputView();

        GameController gameController = new GameController(inputView);
        gameController.start();
    }
}
