package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class GameController {

    public void run() {
        try {
            Dealer dealer = new Dealer();
            Deck deck = new Deck();
            Players players = new Players(InputView.enterNames(), dealer);

            BlackJackController blackJackController = new BlackJackController(deck, dealer,
                players);
            blackJackController.run();
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception);
            run();
        }
    }
}
