import controller.GameController;
import view.InputView;
import view.OutputView;

public class AppConfig {

    public GameController controller() {
        return new GameController(inputView(), outputView());
    }

    private InputView inputView() {
        return new InputView();
    }

    private OutputView outputView() {
        return new OutputView();
    }
}
