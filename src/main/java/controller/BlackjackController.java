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
        Players players = getPlayers();
        Dealer dealer = new Dealer();

        process(players, dealer);
    }

    private Players getPlayers() {
        try {
            return new Players(readPlayersName());
        } catch (RuntimeException exception) {
            printErrorMessage(exception);
            return getPlayers();
        }
    }

    private void process(Players players, Dealer dealer) {
        Map<Player, Stake> playerStakes = readStakes(players);
        drawInitialCards(players, dealer);

        playersHitOrStand(players);
        dealerHitOrStand(dealer);

        printScores(players, dealer);
        getResult(dealer, players, playerStakes);
    }

    private void drawInitialCards(final Players players, final Dealer dealer) {
        printInitialPickGuideMessage(players);
        for (Player player : players.getPlayers()) {
            player.drawCard(Deck.pickCard());
            player.drawCard(Deck.pickCard());
        }
        dealer.drawCard(Deck.pickCard());
        dealer.drawCard(Deck.pickCard());
        printInitialCards(dealer, players);
    }

    private Map<Player, Stake> readStakes(final Players players) {
        Map<Player, Stake> playerStakes = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            repeatReadSingleStake(playerStakes, player);
        }
        return new LinkedHashMap<>(playerStakes);
    }

    private void repeatReadSingleStake(final Map<Player, Stake> playerStakes, final Player player) {
        try {
            playerStakes.put(player, new Stake(readBettingStake(player)));
        } catch (RuntimeException e) {
            InputView.printErrorMessage(e);
            repeatReadSingleStake(playerStakes, player);
        }
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
        } while (canHit && player.isNotBusted());
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
            player.drawCard(Deck.pickCard());
        }
    }

    private void dealerHitOrStand(Dealer dealer) {
        while (dealer.isHittable() && dealer.isNotBusted()) {
            dealerHit(dealer);
        }
    }

    private void dealerHit(Dealer dealer) {
        dealer.drawCard(Deck.pickCard());
        printDealerDrawMessage();
    }

    private void printScores(Players players, Dealer dealer) {
        printScore(dealer);
        for (Player player : players.getPlayers()) {
            printScore(player);
        }
    }

    private void getResult(Dealer dealer, Players players, Map<Player, Stake> playerStakes) {
        Map<Player, DealerStatus> dealerStats = dealer.getDealerStats(players);
        Map<Player, Integer> prizeResults = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            updateDealerPrize(dealer, playerStakes, dealerStats, prizeResults, player);
            updatePlayerPrize(playerStakes, dealerStats, prizeResults, player);
        }
        printResult(prizeResults);
    }

    private void updatePlayerPrize(final Map<Player, Stake> playerStakes, final Map<Player, DealerStatus> dealerStats, final Map<Player, Integer> prizeResults, final Player player) {
        prizeResults.merge(player, playerStakes.get(player).getPlayerPrize(dealerStats.get(player)), Integer::sum);
    }

    private void updateDealerPrize(final Dealer dealer, final Map<Player, Stake> playerStakes, final Map<Player, DealerStatus> dealerStats, final Map<Player, Integer> prizeResults, final Player player) {
        prizeResults.merge(dealer, playerStakes.get(player).getDealerPrize(dealerStats.get(player)), Integer::sum);
    }
}
