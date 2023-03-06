package view;

import domain.Dealer;
import domain.Player;
import domain.Players;
import domain.Result;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String INITIAL_DISTRIBUTE_MESSAGE = "%s와 %s에게 2장을 나누었습니다." + System.lineSeparator();
    private static final String DEALER_DISTRIBUTE_MESSAGE = "%s 16이하라 한장의 카드를 더 받았습니다." + System.lineSeparator();
    private static final String DEALER_CARDS_RESULT_MESSAGE = "%s 카드: %s - 결과: %d" + System.lineSeparator();
    private static final String PLAYER_CARDS_RESULT_MESSAGE = "%s카드: %s - 결과: %d" + System.lineSeparator();
    private static final String DEALER_RESULT_MESSAGE = "%s: %d승 %d무 %d패" + System.lineSeparator();
    private static final String PLAYER_RESULT_MESSAGE = "%s: %s" + System.lineSeparator();
    private static final String SPLIT_DELIMITER = ", ";
    private static final String FINAL_WIN_LOSE_RATIO_MESSAGE = System.lineSeparator()+"## 최종 승패";


    public void printInitialCards(Dealer dealer, Players players) {
        System.out.println();
        System.out.printf(INITIAL_DISTRIBUTE_MESSAGE, dealer.getName(), String.join(SPLIT_DELIMITER, players.getPlayersName()));
        System.out.println(dealer.getName() + ": " + dealer.getInfo().get(dealer.getName()).get(0));
        for (String name : players.getInfo().keySet()) {
            List<String> value = players.getInfo().get(name);
            System.out.println(name + "카드: " + String.join(SPLIT_DELIMITER, value));
        }
    }

    public void printPlayerCardsInfo(Player player) {
        System.out.println(player.getName() + "카드: " +
                String.join(", ", player.getCards()));
    }

    public void printDistributeDealer(Dealer dealer) {
        System.out.println();
        System.out.printf(DEALER_DISTRIBUTE_MESSAGE, dealer.getName());
    }

    public void printCardsResult(Dealer dealer, Players players) {
        System.out.println();
        System.out.printf(DEALER_CARDS_RESULT_MESSAGE, dealer.getName(), String.join(SPLIT_DELIMITER, dealer.getCards()), dealer.getCardsSum());
        for (String name : players.getInfo().keySet()) {
            List<String> value = players.getInfo().get(name);
            System.out.printf(PLAYER_CARDS_RESULT_MESSAGE, name, String.join(SPLIT_DELIMITER, value), players.getCardsSum(name));
        }
    }

    public void printWinnerResult(Map<String, List<Result>> dealerResult, Map<String, Result> playerResult) {
        System.out.println(FINAL_WIN_LOSE_RATIO_MESSAGE);
        String name = dealerResult.keySet().stream().findFirst().get();
        List<Result> dealerResults = dealerResult.get(name);
        System.out.printf(DEALER_RESULT_MESSAGE, name, dealerResults.stream().filter(s -> s == Result.LOSE).count(),
                dealerResults.stream().filter(s -> s == Result.DRAW).count(),
                dealerResults.stream().filter(s -> s == Result.WIN).count());
        for (String key : playerResult.keySet()) {
            System.out.printf(PLAYER_RESULT_MESSAGE, key, playerResult.get(key).getResult());
        }
    }
}
