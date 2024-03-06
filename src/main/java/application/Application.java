package application;

import controller.BlackJackController;
import view.InputView;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final InputView inputView = new InputView(scanner);

        final BlackJackController blackJackController = new BlackJackController(inputView);
        blackJackController.run();
    }
}
