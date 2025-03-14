package blackjack;

import blackjack.controller.BlackJackController;
import blackjack.model.blackjack_player.dealer.judgement.DefaultJudgement;
import blackjack.model.card.initializer.DefaultCardDeckInitializer;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public final class Application {

    public static void main(String[] args) {
        BlackJackController blackJackController =
                new BlackJackController(
                        new InputView(),
                        new OutputView(),
                        new DefaultJudgement(),
                        new DefaultCardDeckInitializer()
                );

        blackJackController.run();
    }

}
