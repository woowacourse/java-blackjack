import controller.BlackjackGameController;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        BlackjackGameController blackjackGameController =
                new BlackjackGameController(new InputView(), new OutputView());
        blackjackGameController.run();
    }
}
