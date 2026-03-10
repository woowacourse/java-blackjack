import controller.BlackJackController;
import view.input.ConsoleInputView;
import view.input.InputView;
import view.output.ConsoleOutputView;
import view.output.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView consoleInputView = new ConsoleInputView();
        OutputView outputView = new ConsoleOutputView();
        BlackJackController controller = new BlackJackController(consoleInputView, outputView);
        controller.run();
    }
}
