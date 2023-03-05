package controller;

import domain.card.Deck;
import domain.player.Dealer;
import domain.player.DealerStatus;
import domain.player.Player;
import domain.player.Players;

import java.util.List;

import static view.InputView.*;
import static view.OutputView.*;

public class BlackjackController {

    private final Deck deck = new Deck();

    public void blackjack() {
        Players players = getPlayers();
        Dealer dealer = new Dealer();

        run(players, dealer);
    }

    private Players getPlayers() {

        try {
            return new Players(readPlayersName());
        } catch (RuntimeException exception) {
            printErrorMessage(exception);
            return getPlayers();
        }
    }

    private void run(Players players, Dealer dealer) {
        drawInitialCards(players, dealer);

        playersHitOrStand(players);
        dealerHitOrStand(dealer);

        printScores(players, dealer);
        getResult(dealer, players);
    }

    private void drawInitialCards(final Players players, final Dealer dealer) {
        printInitialPickGuideMessage(players);
        for (Player player : players.getPlayers()) {
            player.drawCard(deck.pickCard());
            player.drawCard(deck.pickCard());
        }
        dealer.drawCard(deck.pickCard());
        printPlayersCards(dealer, players);
    }

    private void playersHitOrStand(Players players) {
        for (Player player : players.getPlayers()) {
            playerHitOrStand(player);
        }
    }

    private void playerHitOrStand(Player player) {
        boolean canHit;
        do {
            canHit = getIsHit(player);
            playerHit(player, canHit);
            printSinglePlayer(player);
        } while (canHit && !player.isBustedPlayer());
    }

    private boolean getIsHit(Player player) {
        try {
            return readIsHit(player);
        } catch (RuntimeException exception) {
            printErrorMessage(exception);
            return getIsHit(player);
        }
    }

    private void playerHit(Player player, boolean canHit) {
        if (canHit) {
            player.drawCard(deck.pickCard());
        }
    }

    private void dealerHitOrStand(Dealer dealer) {
        while (dealer.isHittable() && !dealer.isBustedPlayer()) {
            dealerHit(dealer);
        }
    }

    private void dealerHit(Dealer dealer) {
        dealer.drawCard(deck.pickCard());
        printDealerDrawMessage();
    }

    private void printScores(Players players, Dealer dealer) {
        printScore(dealer);
        for (Player player : players.getPlayers()) {
            printScore(player);
        }
    }

    private void getResult(Dealer dealer, Players players) {
        List<DealerStatus> dealerStats = dealer.getDealerStats(players);
        printResult(dealerStats, players);
    }
}
