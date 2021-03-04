package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    public void run() {
        Dealer dealer = new Dealer();
        Players players = new Players(InputView.enterNames(), dealer);
        Deck deck = new Deck();

        OutputView.noticeDrawTwoCards(players);
        int count = 2;
        for (int i = 0; i < count; i++) {
            players.giveCards(deck);
        }
        OutputView.noticePlayersCards(players);

        while (players.startTurn(deck)) {
            OutputView.noticePlayersCards(players);
        }
        OutputView.noticePlayersPoint(players);
        OutputView.noticeResult(players);
    }
}
