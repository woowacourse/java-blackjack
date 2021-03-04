package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.GameResult;
import blackjack.domain.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {
    public void run() {
        Dealer dealer = new Dealer();
        Players players = new Players(InputView.enterNames(), dealer);

        OutputView.noticeDrawTwoCards(players);
        Deck deck = new Deck();

        int count = 2;
        for (int i = 0; i < count; i++) {
            players.giveCards(deck);
        }

        OutputView.noticePlayersCards(players);

        while (players.startTurn(deck))

        OutputView.noticePlayersPoint(players);
        OutputView.noticeResult(players);
    }
}
