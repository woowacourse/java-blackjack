import controller.GameController;
import view.InputView;
import view.OutputView;

public class Main {
    public static void main(String[] args) {
        GameController gameController = new GameController(new InputView(), new OutputView());
        gameController.run();
    }
}
