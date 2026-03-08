package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.service.GameService;
import blackjack.view.InputView;

public class Application {

    public static void main(String[] args) {
        BlackjackController controller = new BlackjackController(new GameService(), new InputView());
        controller.run();
    }

}
