import controller.BlackJackController;
import view.ConsoleInputView;
import view.ConsoleOutputView;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {

        InputView inputView = new ConsoleInputView();
        OutputView outputView = new ConsoleOutputView();

        BlackJackController controller = new BlackJackController(inputView, outputView);
        controller.run();
    }
}
