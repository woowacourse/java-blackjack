package controller;

import domain.card.Deck;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;
import domain.stake.Bet;
import view.InputView;

import java.util.LinkedHashMap;
import java.util.Map;

import static view.InputView.*;
import static view.OutputView.*;

public final class BlackjackController {

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

    private void process(final Players players, final Dealer dealer, final Deck deck) {
        Map<Player, Bet> playerStakes = readStakes(players);
        drawInitialCards(players, dealer, deck);

        readPlayersHit(players, deck);
        drawIfDealerCanHit(dealer, deck);

        showFinalScores(players, dealer);
        showBetResults(dealer, players, playerStakes);
    }

    private void drawInitialCards(final Players players, final Dealer dealer, final Deck deck) {
        printInitialPickGuideMessage(players);
        for (Player player : players.getPlayers()) {
            player.drawInitialCards(deck);
        }
        dealer.drawInitialCards(deck);
        printInitialCards(dealer, players);
    }

    private Map<Player, Bet> readStakes(final Players players) {
        Map<Player, Bet> playerStakes = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            repeatReadSingleStake(playerStakes, player);
        }
        return playerStakes;
    }

    private void repeatReadSingleStake(final Map<Player, Bet> playerStakes, final Player player) {
        try {
            playerStakes.put(player, Bet.from(readBet(player)));
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

    private void showFinalScores(final Players players, final Dealer dealer) {
        printScore(dealer);
        for (Player player : players.getPlayers()) {
            printScore(player);
        }
    }

    private void showBetResults(final Dealer dealer, final Players players, final Map<Player, Bet> playerBets) {
        Map<Player, Bet> finalStakeResults = players.calculateFinalResults(dealer, playerBets);
        printResult(finalStakeResults);
    }
}
