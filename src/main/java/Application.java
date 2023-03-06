import controller.BlackJackController;
import view.InputValidator;
import view.InputView;
import view.OutputView;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView(new Scanner(System.in), new InputValidator());
        OutputView outputView = new OutputView();

        new BlackJackController(inputView, outputView).run();
    }
}
