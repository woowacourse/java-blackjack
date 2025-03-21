package blackjack;

import blackjack.controller.GameManager;
import blackjack.view.Input;
import blackjack.view.Output;

public class Application {
    public static void main(String[] args) {
        Input input = new Input();
        Output output = new Output();

        GameManager gameManager = new GameManager(input, output);
        gameManager.runBlackjack();
    }
}
