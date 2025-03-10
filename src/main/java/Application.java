import controller.BlackJackController;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        BlackJackController controller = new BlackJackController(inputView, outputView);
        controller.run();
    }
}
