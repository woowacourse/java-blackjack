import controller.BlackJackController;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        BlackJackController controller = new BlackJackController(
                new InputView(),
                new OutputView()
        );
        controller.run();
    }
}
