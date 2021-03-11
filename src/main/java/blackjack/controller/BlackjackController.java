package blackjack.controller;

import blackjack.domain.card.CardManager;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    public void run() {
        final CardManager cardManager = CardManager.create();

        final List<String> playersNames = InputView.receiveNames();
        final List<Integer> playersMoney = InputView.receiveMoney(playersNames);

        final Players players =cardManager.initiateGamers(playersNames, playersMoney);
        final Dealer dealer = new Dealer(cardManager.giveFirstHand());
        OutputView.gameStart(players, dealer);

        for (Player player : players) {
            playerHitOrStand(cardManager, player);
        }
        dealerHitOrStand(cardManager, dealer);
        printResult(players, dealer);
    }

    private void playerHitOrStand(final CardManager cardManager, final Player player) {
        while (InputView.receiveAnswer(player.getName())) {
            player.receiveCard(cardManager.giveCard());
            OutputView.allCards(player);
        }
    }

    private void dealerHitOrStand(final CardManager cardManager, final Dealer dealer) {
        if (dealer.checkBoundary()) {
            dealer.receiveCard(cardManager.giveCard());
            OutputView.dealerHit();
        }
    }

    private void printResult(final Players players, final Dealer dealer) {
        OutputView.gamersAllCards(players, dealer);
        OutputView.printResultTitle();
        OutputView.dealerResult(players.dealerResult(dealer));
        OutputView.playersResult(players.resultWithName(dealer));
    }
}
