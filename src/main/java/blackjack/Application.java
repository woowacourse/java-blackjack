package blackjack;

import blackjack.controller.Betting;
import blackjack.controller.BlackjackGame;
import blackjack.domain.CardDeck;
import blackjack.domain.Dealer;
import blackjack.domain.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {

    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame();

        blackjackGame.run();
    }
}
