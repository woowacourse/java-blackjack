import controller.BlackJackController;
import view.InputView;
import view.OutputView;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        BlackJackController blackJackController = new BlackJackController(inputView, outputView);
        blackJackController.run();
    }
}
