import controller.BlackjackController;
import ui.InputView;
import ui.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        BlackjackController blackjackController = new BlackjackController(inputView, outputView);
        blackjackController.start();
    }
}
