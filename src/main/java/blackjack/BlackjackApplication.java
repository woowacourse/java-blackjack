package blackjack;

import blackjack.config.BlackjackConfig;
import blackjack.controller.BlackjackController;

public class BlackjackApplication {

    public static void main(String[] args) {
        BlackjackConfig config = new BlackjackConfig();
        BlackjackController controller = config.controller();

        controller.run();
    }
}
