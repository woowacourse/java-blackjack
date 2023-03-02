package controller;

import domain.*;
import view.OutputView;

import java.util.LinkedHashMap;

import static view.InputView.readIsHit;
import static view.InputView.readPlayersName;
import static view.OutputView.*;

public class Controller {
    //TODO: Player에 다시 뽑는지 아닌지 field 선언하기.
    //TODO: 21넘는 Gambler는 패배처리

    public void blackjackGame() {
        Players players = new Players(readPlayersName());
        Dealer dealer = new Dealer(new Cards());

        printInitialPickGuideMessage(players);
        printGamblersCards(players, dealer);

        boolean isGameEnd;
        do {
            isGameEnd = false;

            for (Player player : players.getPlayers()) {
                boolean isHit = readIsHit(player);
                if (isHit) {
                    player.pickCard();
                    isGameEnd = true;
                }

                OutputView.printSingleGambler(player);
            }
        } while (isGameEnd);

        while (dealer.getScore() <= 16) {
            dealer.pickCard();
            System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }

        printScores(players, dealer);

        LinkedHashMap<Gambler, Integer> result = calculateWinCount(dealer, players);
        OutputView.printResult(result);
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
        } else {
            result.put(player, 0);
            result.replace(dealer, result.get(dealer) + 1);
        }
    }

    private boolean isPlayerWin(Dealer dealer, Player player) {
        return dealer.getScore() <= player.getScore();
    }
}
