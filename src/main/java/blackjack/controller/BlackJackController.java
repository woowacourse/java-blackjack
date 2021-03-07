package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Gamer;
import blackjack.domain.Players;
import blackjack.view.OutputView;

public class BlackJackController {

    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public BlackJackController(Deck deck, Dealer dealer, Players players) {
        this.deck = deck;
        this.dealer = dealer;
        this.players = players;
    }

    public void run() {
        try {
            dealInitCard(players, deck);
            dealCard(players, deck);
            printMatchResult(players);
        } catch (IllegalArgumentException exception) {

        }
    }

    private void printMatchResult(Players players) {
        OutputView.noticePlayersPoint(players);
        OutputView.noticeResult(players);
    }

    private void dealCard(Players players, Deck deck) {
        while (players.startTurn(deck)) {
            OutputView.noticePlayersCards(players);
        }
    }

    private void dealInitCard(Players players, Deck deck) {
        OutputView.noticeDrawTwoCards(players);
        int bound = Gamer.NUM_INIT_CARD;
        for (int i = 0; i < bound; i++) {
            players.giveCards(deck);
        }
        OutputView.noticePlayersCards(players);
    }
}
