package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.domain.GameManager;
import blackjack.domain.card.RandomBlackjackShuffle;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {

    public static void main(String[] args) {
        BlackjackController controller = new BlackjackController(
                new InputView(),
                new OutputView(),
                new GameManager(new RandomBlackjackShuffle()));
        
        controller.start();
    }
}
