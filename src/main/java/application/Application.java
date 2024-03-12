package application;

import controller.BlackJackController;
import java.util.Scanner;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final InputView inputView = new InputView(scanner);
        final OutputView outputView = new OutputView();
        final BlackJackController blackJackController = new BlackJackController(inputView, outputView);
        blackJackController.run();
    }
}
