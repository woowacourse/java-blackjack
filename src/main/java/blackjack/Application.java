package blackjack;

import blackjack.controller.BlackJackController;
import blackjack.domain.game.BlackJackGame;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView(new Scanner(System.in));
        OutputView outputView = new OutputView();
        BlackJackGame blackJackGame = new BlackJackGame();

        BlackJackController blackJackController = new BlackJackController(inputView, outputView, blackJackGame);
        blackJackController.run();
    }
}
