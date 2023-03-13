package blackjack.view;

import java.util.List;
import java.util.Map;

public class OutputView {

    public static final String PRINT_DELIMITER = ": ";
    private static OutputView instance;

    private OutputView() {
    }

    public static OutputView getInstance() {
        if (instance == null) {
            instance = new OutputView();
            return instance;
        }
        return instance;
    }

    public void printDistributionMessage(List<String> players) {
        String names = String.join(",", players);
        System.out.println("딜러와 " + names + "에게 2장을 나누었습니다.");
    }

    public void printHandCardUnits(Map<String, List<String>> playerHand) {
        for (Map.Entry<String, List<String>> entry : playerHand.entrySet()) {
            String name = entry.getKey();
            String hand = String.join(",", entry.getValue());
            System.out.println(name + PRINT_DELIMITER + hand);
        }
    }

    public void printDealerHitMessage() {
        System.out.println();
        System.out.println("딜러가 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printScoreResult(Map<String, List<String>> hand, String result) {
        for (Map.Entry<String, List<String>> entry : hand.entrySet()) {
            String name = entry.getKey();
            String cards = String.join(",", entry.getValue());
            System.out.println(name + PRINT_DELIMITER + cards + " - 결과: " + result);
        }
    }

    public void printProfitResultMessage() {
        System.out.println();
        System.out.println("## 최종 수익");
    }


    public void printProfitResult(String name, double profit) {
        System.out.println(name + PRINT_DELIMITER + profit);
    }

    public void printDealerWinningResult(List<Integer> result) {
        System.out.println("딜러" + PRINT_DELIMITER + result.get(0) + "승 " + result.get(1) + "무 " + result.get(2) + "패");
    }

    public void printPlayersWinningResult(String name, List<Integer> result) {
        System.out.println(name + ": " + winningResult(result));
    }

    private String winningResult(List<Integer> result) {
        if (result.get(0) > 0) {
            return "승";
        }
        if (result.get(1) > 0) {
            return "무";
        }
        return "패";
    }
}
