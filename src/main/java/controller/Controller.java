package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.LinkedHashMap;
import java.util.Map;

import static view.InputView.*;
import static view.OutputView.*;

public class Controller {
    private static final String DEALER_HIT = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.";

    public void start() {
        Players players = getPlayers();
        Dealer dealer = new Dealer(new Cards());

        startBlackjack(players, dealer);
    }

    private Players getPlayers() {
        Players players;

        try {
            players = new Players(readPlayersName());
        } catch (RuntimeException exception) {
            printErrorMessage(exception);
            players = getPlayers();
        }
        return players;
    }

    private void startBlackjack(Players players, Dealer dealer) {
        printInitialPickGuideMessage(players);
        printGamblersCards(players, dealer);

        playersHitOrStand(players);
        dealerHitOrStand(dealer);

        printScores(players, dealer);
        Map<Gambler, Integer> result = getResult(dealer, players);
        OutputView.printResult(result);
    }

    private void playersHitOrStand(Players players) {
        for (Player player : players.getPlayers()) {
            playerHitOrStand(player);
        }
    }

    private void playerHitOrStand(Player player) {
        boolean isHit;
        do {
            isHit = getIsHit(player);
            playerHit(player, isHit);
            OutputView.printSingleGambler(player);
            isHit = isPickAble(player, isHit);
        } while (isHit);
    }

    private boolean isPickAble(Player player, boolean isHit) {
        if (player.isBustedGambler(player.getScore())) {
            isHit = false;
        }
        return isHit;
    }

    private boolean getIsHit(Player player) {
        boolean isHit;
        try {
            isHit = readIsHit(player);
        } catch (RuntimeException exception) {
            InputView.printErrorMessage(exception);
            isHit = getIsHit(player);
        }
        return isHit;
    }

    private void playerHit(Player player, boolean isHit) {
        if (isHit) {
            player.pickCard();
        }
    }

    private void dealerHitOrStand(Dealer dealer) {
        while (dealer.getScore() <= dealer.getPickBoundary()) {
            dealerHit(dealer);
        }
    }

    private void dealerHit(Dealer dealer) {
        dealer.pickCard();
        System.out.println(DEALER_HIT);
    }

    private void printScores(Players players, Dealer dealer) {
        printScore(dealer);
        for (Player player : players.getPlayers()) {
            printScore(player);
        }
    }

    private Map<Gambler, Integer> getResult(Dealer dealer, Players players) {
        Map<Gambler, Integer> result = new LinkedHashMap<>();
        return calculateWinCount(dealer, players, result);
    }

    private Map<Gambler, Integer> calculateWinCount(Dealer dealer, Players players, Map<Gambler, Integer> result) {
        result.put(dealer, 0);
        for (Player player : players.getPlayers()) {
            decideWinner(dealer, result, player);
        }
        return result;
    }

    private void decideWinner(Dealer dealer, Map<Gambler, Integer> result, Player player) {
        if (isPlayerWin(dealer, player)) {
            result.put(player, 1);
            return;
        }

        if (isDealerWin(dealer, player)) {
            result.put(player, 0);
            result.replace(dealer, result.get(dealer) + 1);
        }
    }

    //TODO: controller에서 getter 제거 하기
    private boolean isPlayerWin(Dealer dealer, Player player) {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();
        return (dealerScore <= playerScore && !player.isBustedGambler(playerScore)) || dealer.isBustedGambler(dealerScore);
    }

    private boolean isDealerWin(Dealer dealer, Player player) {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();
        return dealerScore > playerScore || player.isBustedGambler(playerScore);
    }
}
