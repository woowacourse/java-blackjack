package controller;

import domain.card.Deck;
import domain.player.Dealer;
import domain.player.DealerStatus;
import domain.player.Player;
import domain.player.Players;
import domain.stake.Stake;
import view.InputView;

import java.util.LinkedHashMap;
import java.util.Map;

import static view.InputView.*;
import static view.OutputView.*;

public class BlackjackController {

    public void runGame() {
        Players players = repeatReadPlayers();
        Dealer dealer = new Dealer();
        Deck deck = new Deck();

        process(players, dealer, deck);
    }

    private Players repeatReadPlayers() {
        try {
            return new Players(readPlayersName());
        } catch (RuntimeException exception) {
            printErrorMessage(exception);
            return repeatReadPlayers();
        }
    }

    private void process(Players players, Dealer dealer, Deck deck) {
        Map<Player, Stake> playerStakes = readStakes(players);
        drawInitialCards(players, dealer, deck);

        readPlayersHit(players, deck);
        drawIfDealerCanHit(dealer, deck);

        printScores(players, dealer);
        getResult(dealer, players, playerStakes);
    }

    private void drawInitialCards(final Players players, final Dealer dealer, final Deck deck) {
        printInitialPickGuideMessage(players);
        for (Player player : players.getPlayers()) {
            player.initDraw(deck);
        }
        dealer.initDraw(deck);
        printInitialCards(dealer, players);
    }

    private Map<Player, Stake> readStakes(final Players players) {
        Map<Player, Stake> playerStakes = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            repeatReadSingleStake(playerStakes, player);
        }
        return playerStakes;
    }

    private void repeatReadSingleStake(final Map<Player, Stake> playerStakes, final Player player) {
        try {
            playerStakes.put(player, Stake.fromBet(readBettingStake(player)));
        } catch (RuntimeException e) {
            InputView.printErrorMessage(e);
            repeatReadSingleStake(playerStakes, player);
        }
    }

    private void readPlayersHit(final Players players, final Deck deck) {
        for (Player player : players.getPlayers()) {
            readPlayerHit(player, deck);
        }
    }

    private void readPlayerHit(final Player player, final Deck deck) {
        boolean canHit;
        do {
            canHit = repeatReadCanHit(player);
            drawIfHit(player, canHit, deck);
            printSinglePlayer(player);
        } while (canHit && player.isNotBusted());
    }

    private boolean repeatReadCanHit(final Player player) {
        try {
            return readIsHit(player);
        } catch (RuntimeException exception) {
            printErrorMessage(exception);
            return repeatReadCanHit(player);
        }
    }

    private void drawIfHit(final Player player, final boolean canHit, final Deck deck) {
        if (canHit) {
            player.drawCard(deck.pickCard());
        }
    }

    private void drawIfDealerCanHit(final Dealer dealer, final Deck deck) {
        while (dealer.isHittable()) {
            printDealerDrawMessage();
            dealer.drawCard(deck.pickCard());
        }
    }

    private void printScores(Players players, Dealer dealer) {
        printScore(dealer);
        for (Player player : players.getPlayers()) {
            printScore(player);
        }
    }

    private void getResult(Dealer dealer, Players players, Map<Player, Stake> playerStakes) {
        Map<Player, DealerStatus> dealerStats = dealer.getDealerStats(players);
        Map<Player, Stake> playerStakeMap = dealer.calculateBets(players, dealerStats, playerStakes);
        printResult(playerStakeMap);
    }
}
