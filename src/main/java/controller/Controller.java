package controller;

import domain.*;

import java.util.LinkedHashMap;

import static view.InputView.*;
import static view.OutputView.*;

public class Controller {

    private final Deck deck = new Deck();

    public void blackjack() {
        Players players = getPlayers();
        Dealer dealer = new Dealer();

        gameStart(players, dealer);
    }

    private Players getPlayers() {

        try {
            return new Players(readPlayersName());
        } catch (RuntimeException exception) {
            printErrorMessage(exception);
            return getPlayers();
        }
    }

    private void gameStart(Players players, Dealer dealer) {
        drawInitialCards(players, dealer);

        playersHitOrStand(players);
        dealerHitOrStand(dealer);

        printScores(players, dealer);
        LinkedHashMap<Player, Integer> result = getResult(dealer, players);
        printResult(result);
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
        } while (canHit && !isBustedPlayer(player));
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

    private boolean isBustedPlayer(Player player) {
        return player.getScore()
                .isBusted();
    }

    private void dealerHitOrStand(Dealer dealer) {
        while (dealer.isHittable()) {
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

    private LinkedHashMap<Player, Integer> getResult(Dealer dealer, Players players) {
        LinkedHashMap<Player, Integer> result = new LinkedHashMap<>();
        return calculateWinCount(dealer, players, result);
    }

    private LinkedHashMap<Player, Integer> calculateWinCount(Dealer dealer, Players players, LinkedHashMap<Player, Integer> result) {
        result.put(dealer, 0);
        for (Player player : players.getPlayers()) {
            decideWinner(dealer, result, player);
        }
        return result;
    }

    private void decideWinner(Dealer dealer, LinkedHashMap<Player, Integer> result, Player player) {
        if (isPlayerWin(dealer, player)) {
            result.put(player, 1);
            return;
        }

        if (isDealerWin(dealer, player)) {
            result.put(player, 0);
            result.replace(dealer, result.get(dealer) + 1);
        }
    }

    private boolean isPlayerWin(Dealer dealer, Player player) {
        int playerScore = player.getScore().getValue();
        int dealerScore = dealer.getScore().getValue();
        return (dealerScore <= playerScore && !player.isBustedPlayer()) || dealer.isBustedPlayer();
    }

    private boolean isDealerWin(Dealer dealer, Player player) {
        int playerScore = player.getScore().getValue();
        int dealerScore = dealer.getScore().getValue();
        return dealerScore > playerScore || player.isBustedPlayer();
    }
}
