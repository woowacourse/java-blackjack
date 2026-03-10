package blackjack;

import blackjack.config.AppConfig;
import blackjack.controller.BlackjackController;
import blackjack.util.Console;

public class Application {

    public static void main(String[] args) {
        try {
            AppConfig config = new AppConfig();
            BlackjackController controller = config.controller();
            controller.run();
        } finally {
            Console.close();
        }
    }

}
