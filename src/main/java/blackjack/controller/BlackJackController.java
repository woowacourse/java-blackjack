package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    public void run() {
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        try {
            Players players = new Players(InputView.enterNames(), dealer);
            dealTwoCard(players, deck);
            dealWantCard(players, deck);
            printMatchResult(players);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            run();
        }
    }

    private void printMatchResult(Players players) {
        OutputView.noticePlayersPoint(players);
        OutputView.noticeResult(players);
    }

    private void dealWantCard(Players players, Deck deck) {
        while (players.startTurn(deck)) {
            OutputView.noticePlayersCards(players);
        }
    }

    private void dealTwoCard(Players players, Deck deck) {
        OutputView.noticeDrawTwoCards(players);
        int count = 2;
        for (int i = 0; i < count; i++) {
            players.giveCards(deck);
        }
        OutputView.noticePlayersCards(players);
    }
}
