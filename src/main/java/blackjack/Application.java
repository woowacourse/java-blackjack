package blackjack;

import blackjack.controller.Controller;

public final class Application {

    public static void main(String[] args) {
        final Controller controller = new Controller();
        controller.run();
    }
}
