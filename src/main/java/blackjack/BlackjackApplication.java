package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.service.BlackjackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackApplication {

    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController(
                new InputView(),
                new OutputView(),
                new BlackjackService()
        );
        
        blackjackController.run();
    }
}
