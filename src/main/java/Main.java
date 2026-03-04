import controller.GameController;
import view.InputView;

public class Main {
    public static void main(String[] args) {
        GameController gameController = new GameController(new InputView());
        gameController.run();
    }
}
