package controller;

import domain.*;
import view.OutputView;

import java.util.LinkedHashMap;

import static view.InputView.readIsHit;
import static view.InputView.readPlayersName;
import static view.OutputView.*;

public class Controller {
    private static final String DEALER_HIT = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.";
    //TODO: 21넘는 Gambler는 패배처리

    public void blackjack() {
        Players players = new Players(readPlayersName());
        Dealer dealer = new Dealer(new Cards());

        gameStart(players, dealer);
    }

    private void gameStart(Players players, Dealer dealer) {
        printInitialPickGuideMessage(players);
        printGamblersCards(players, dealer);

        playersHitOrStand(players);
        dealerHitOrStand(dealer);

        printScores(players, dealer);
        LinkedHashMap<Gambler, Integer> result = calculateWinCount(dealer, players);
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
            isHit = readIsHit(player);
            playerHit(player, isHit);
            OutputView.printSingleGambler(player);
        } while (isHit);
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

    private LinkedHashMap<Gambler, Integer> calculateWinCount(Dealer dealer, Players players) {
        LinkedHashMap<Gambler, Integer> result = new LinkedHashMap<>();
        mapInitSetting(dealer, players, result);

        return result;
    }

    private void mapInitSetting(Dealer dealer, Players players, LinkedHashMap<Gambler, Integer> result) {
        result.put(dealer, 0);
        for (Player player : players.getPlayers()) {
            decideWinner(dealer, result, player);
        }

    }

    private void decideWinner(Dealer dealer, LinkedHashMap<Gambler, Integer> result, Player player) {
        if (isPlayerWin(dealer, player)) {
            result.put(player, 1);
        }

        if(isPlayerLose(dealer,player)){
            result.put(player, 0);
            result.replace(dealer, result.get(dealer) + 1);
        }
    }

    private boolean isPlayerWin(Dealer dealer, Player player) {
        return dealer.getScore() <= player.getScore();
    }

    private boolean isPlayerLose(Dealer dealer, Player player) {
        return dealer.getScore() > player.getScore();
    }
}
