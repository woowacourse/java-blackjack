import controller.GameController;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        GameController gameController = new GameController(outputView);
        gameController.start();
    }
}
