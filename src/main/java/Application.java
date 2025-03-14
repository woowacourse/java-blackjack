import controller.BlackjackConsole;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        BlackjackConsole blackjackController = new BlackjackConsole(new OutputView(), new InputView());
        blackjackController.run();
    }
}
