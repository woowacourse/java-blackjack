package blackjack;

import blackjack.util.ConsoleReader;
import blackjack.view.InputView;

public class Application {
    public static void main(String[] args) {
        ConsoleReader consoleReader = new ConsoleReader();
        try {
            BlackJackGame game = new BlackJackGame(new InputView(consoleReader));
            game.run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
