package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {
    public void run() {
        Dealer dealer = new Dealer();
        Players players = new Players(InputView.enterNames(), dealer);
        Deck deck = new Deck();

        drawCards(players, dealer, deck);
        drawUntilPossible(dealer, players, deck);

        OutputView.noticePlayersPoint(dealer, players);
        OutputView.noticeResult(players);
    }

    private void drawCards(Players players, Dealer dealer, Deck deck) {
        OutputView.noticeDrawTwoCards(players);
        GameManager gameManager = new GameManager(deck, players);
        gameManager.giveCards();
        OutputView.noticePlayersCards(dealer, players);
    }

    private void drawUntilPossible(Dealer dealer, Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            playEachPlayer(deck, player);
        }
        while (dealer.canReceiveCard()) {
            dealer.keepDrawing(deck);
            OutputView.noticeDealerReceiveCard();
        }
    }

    private void playEachPlayer(Deck deck, Player player) {
        while (player.canReceiveCard() && player.continueDraw(deck, InputView.isContinueDraw(player))) {
            player.playEachPlayer(deck);
            OutputView.noticePlayerCards(player);
        }
    }
}
