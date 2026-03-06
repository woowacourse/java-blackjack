package blackjack.view;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class OutputView {

    private OutputView() {
    }

    public static void printGameSettingMessage(String dealerName, List<String> playersName) {
        System.out.println();
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (String playerName : playersName) {
            stringJoiner.add(playerName);
        }

        System.out.println(dealerName + "와 " + stringJoiner + "에게 2장을 나누었습니다.");
    }

    public static void printSettingCardsResult(String userName, List<String> cards) {
        System.out.print(userName + "카드: ");

        StringJoiner stringJoiner = new StringJoiner(", ");
        for (String card : cards) {
            stringJoiner.add(card);
        }

        System.out.println(stringJoiner);
    }

    // TODO: 16점 고정 상수 제거
    public static void printGetMoreCardsForDealer(String dealerName) {
        System.out.println(dealerName + "는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public static void printCardsResult(String userName, List<String> cards, int cardsValue) {
        System.out.print(userName + "카드: ");

        StringJoiner stringJoiner = new StringJoiner(", ");
        for (String card : cards) {
            stringJoiner.add(card);
        }

        System.out.println(stringJoiner + " - 결과: " + cardsValue);
    }

    public static void printWinningResult(Map<String, Boolean> result, String dealerName, int dealerWinCount) {
        System.out.println("## 최종 승패");
        System.out.println(dealerName + ": " + dealerWinCount + "승 " + (result.size() - dealerWinCount) + "패");

        for (String userName : result.keySet()) {
            String flag = "패";
            if (result.get(userName)) {
                flag = "승";
            }
            System.out.println(userName + ": " + flag);
        }
    }

}
