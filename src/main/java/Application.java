import controller.BlackjackController;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController(new InputView(), new OutputView());
        blackjackController.startBlackjack();
    }
}
