import blackjack.controller.BlackjackController;
import blackjack.ui.InputView;
import blackjack.ui.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        BlackjackController blackjackController = new BlackjackController(inputView, outputView);
        blackjackController.start();
    }
}
