package blackjack;

import blackjack.util.ConsoleReader;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ConsoleReader consoleReader = new ConsoleReader();
        try {
            BlackJackGame game = new BlackJackGame(new InputView(consoleReader), new OutputView());
            game.run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
