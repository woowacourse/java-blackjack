import controller.BlackJackController;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        BlackJackController blackjackController = new BlackJackController(new InputView(), new OutputView());

        blackjackController.run();
    }
}
