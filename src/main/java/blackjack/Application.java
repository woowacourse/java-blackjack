package blackjack;

import blackjack.controller.BlackjackGame;
import blackjack.controller.Result;
import blackjack.domain.Dealer;
import blackjack.domain.Players;

public class Application {

    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame();
        Result result = new Result();

        Dealer dealer = new Dealer();

        Players players = blackjackGame.play(dealer);

        result.openResult(dealer, players);
        result.win(dealer, players);
    }
}
