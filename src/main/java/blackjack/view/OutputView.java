package blackjack.view;

import java.util.List;
import java.util.StringJoiner;

public class OutputView {

    private OutputView() {
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

}
