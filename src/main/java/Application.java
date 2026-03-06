import controller.GameController;
import domain.GameManager;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        GameManager manager = new GameManager();

        GameController controller = new GameController(manager, inputView, outputView);
        controller.run();
    }
}
