import controller.GameManager;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        GameManager gameManager = new GameManager(inputView, outputView);
        gameManager.run();
    }
}
