package controller;

import domain.*;
import view.OutputView;

import java.util.HashMap;
import java.util.Map;

import static view.InputView.readPlayersName;
import static view.InputView.readStand;
import static view.OutputView.printInitialPickGuideMessage;
import static view.OutputView.printScore;

public class Controller {
    //TODO: Player에 다시 뽑는지 아닌지 field 선언하기.
    //TODO: 21넘는 Gambler는 패배처리

    public void blackjackGame() {
        Players players = new Players(readPlayersName());
        Dealer dealer = new Dealer(new Cards());
        printInitialPickGuideMessage(players);
        OutputView.printGamblersCards(players, dealer);

        boolean isStopHit;
        do {
            isStopHit = false;

            for (Player player : players.getPlayers()) {
                boolean isHit = readStand(player);
                if (isHit) {
                    player.pickCard();
                    isStopHit = true;
                }

                OutputView.printSingleGambler(player);
            }
        } while (isStopHit);

        while (dealer.getScore() <= 16) {
            dealer.pickCard();
            System.out.println("\n 딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
        }

        printScore(dealer);
        for (Player player : players.getPlayers()) {
            printScore(player);
        }


        Map<Gambler, Integer> result = calculateWinCount(dealer, players);
        //비교 로직 구현해서 result 채우기
        OutputView.printResult(result);
    }

    private Map<Gambler, Integer> calculateWinCount(Dealer dealer, Players players) {
        Map<Gambler, Integer> result = new HashMap<>();
        mapInitSetting(dealer, players, result);

        return result;
    }

    private void mapInitSetting(Dealer dealer, Players players, Map<Gambler, Integer> result) {
        result.put(dealer, 0);
        for (Player player : players.getPlayers()) {
            decideWinner(dealer, result, player);
        }

    }

    private void decideWinner(Dealer dealer, Map<Gambler, Integer> result, Player player) {
        if (isPlayerWin(dealer, player)) {
            result.put(player, 1);
        } else {
            result.put(player, 0);
            result.put(dealer, result.get(dealer) + 1);
        }
    }

    private boolean isPlayerWin(Dealer dealer, Player player) {
        return dealer.getScore() <= player.getScore();
    }
}
