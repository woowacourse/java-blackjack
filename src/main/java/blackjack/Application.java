package blackjack;

import blackjack.controller.Game;

public class Application {

    public static void main(String[] args) {
        Game blackjack = new Game();
        blackjack.run();
    }
}
