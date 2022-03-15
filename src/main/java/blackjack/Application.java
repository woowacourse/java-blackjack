package blackjack;

import blackjack.controller.Game;
import blackjack.controller.Playing;

public class Application {

    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}
