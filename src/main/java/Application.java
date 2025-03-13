import controller.BlackjackGame;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        BlackjackGame blackjackController = new BlackjackGame(new OutputView(), new InputView());
        blackjackController.run();
    }
}
