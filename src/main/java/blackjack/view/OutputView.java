package blackjack.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int ZERO = 0;

    public void printGameStartMessage(Map<String, List<String>> players, String dealerCards) {
        List<String> playersName = new ArrayList<>(players.keySet());

        System.out.println(LINE_SEPARATOR
                + "딜러와 "
                + String.join(", ", playersName)
                + "에게 2장을 나누었습니다.");
        System.out.println("딜러: " + dealerCards);
        printPlayersInformation(players, playersName);
    }

    private void printPlayersInformation(Map<String, List<String>> players, List<String> playersName) {
        for (int i = 0; i < players.size(); i++) {
            System.out.println(playersName.get(i)
                    + ": "
                    + String.join(", ", players.get(playersName.get(i))));
        }
    }

    public void printCards(String name, List<String> cards) {
        System.out.println(name + "카드: " + String.join(", ", cards));
    }

    public void printDealerHit() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }


    public void printDealerResult(List<String> dealerCards, int calculateScore) {
        System.out.println("딜러 카드: " + String.join(", ", dealerCards) + " - 결과: " + calculateScore);
    }

    public void printPlayerResult(Map<String, List<String>> players, List<Integer> calculateScore) {
        List<String> playersName = new ArrayList<>(players.keySet());
        for (int i = 0; i < players.size(); i++) {
            System.out.println(playersName.get(i)
                    + ": "
                    + String.join(", ", players.get(playersName.get(i)))
                    + " - 결과: "
                    + calculateScore.get(i));
        }
    }

    public void printEndMsg() {
        System.out.println();
        System.out.println("## 최종 승패");
    }

    public void printPlayerWinningResult(Map<String, String> playerResult) {
        for (String playerName : playerResult.keySet()) {
            System.out.println(playerName + ": " + playerResult.get(playerName));
        }
    }

    public void printDealerWinningResult(Map<String, Integer> dealerResult) {
        StringBuilder dealerResultMsg = new StringBuilder("딜러: ");
        for (String result : dealerResult.keySet()) {
            if (dealerResult.get(result) != ZERO) {
                dealerResultMsg.append(dealerResult.get(result)).append(result).append(" ");
            }
        }
        System.out.println(dealerResultMsg);
    }
}
