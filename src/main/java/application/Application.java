package application;

import controller.BlackJackController;
import controller.InputController;
import java.util.Scanner;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final InputView inputView = new InputView(scanner);
        final OutputView outputView = new OutputView();
        final InputController inputController = new InputController(inputView, outputView);
        final BlackJackController blackJackController = new BlackJackController(inputController, outputView);
        blackJackController.run();
    }
}
