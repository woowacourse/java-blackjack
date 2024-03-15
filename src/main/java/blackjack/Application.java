package blackjack;

import blackjack.controller.GameManager;
import blackjack.view.InputView;
import blackjack.view.MessageResolver;
import blackjack.view.OutputView;

public class Application {

    public static void main(String[] args) {
        GameManager gameManager = new GameManager(new InputView(), new OutputView(new MessageResolver()));
        gameManager.start();
    }
}
