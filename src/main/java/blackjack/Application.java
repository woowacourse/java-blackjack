package blackjack;

import blackjack.controller.GameManager;

public class Application {

    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.start();
    }
}
