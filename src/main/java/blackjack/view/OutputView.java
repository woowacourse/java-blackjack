package blackjack.view;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;

import blackjack.domain.GameResult;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final int DEALER_DRAW_BOUNDARY = 16;

    private OutputView() {
    }

    public static void printDefaultDrawCardMessage(List<String> personNames) {
        final String names = String.join(", ", personNames);
        System.out.println("딜러와 " + names + "에게 2장을 나누었습니다.");
    }

    public static void printCardsStatus(String personName, List<String> cards) {
        System.out.println(getCardStatus(personName, cards));
    }

    private static String getCardStatus(String personName, List<String> cards) {
        final String allCards = String.join(", ", cards);
        return personName + " 카드: " + allCards;
    }

    public static void printCardsStatus(String personName, List<String> cards, int score) {
        System.out.println(getCardStatus(personName, cards) + "- 결과: " + score);
    }

    public static void printDealerDrawCardMessage(int score) {
        if (score > DEALER_DRAW_BOUNDARY) {
            System.out.println("딜러는 " + DEALER_DRAW_BOUNDARY + "초과라 카드를 받지 않았습니다.");
            return;
        }
        System.out.println("딜러는 " + DEALER_DRAW_BOUNDARY + "이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printGameEndMessage() {
        System.out.println("## 최종 승패");
    }

    public static void printDealerResult(List<GameResult> gameResults) {
        Map<String, Long> collect = gameResults.stream()
                .collect(groupingBy(GameResult::getName, counting()));
        String result = collect.entrySet().stream()
                .map(entry -> entry.getValue() + entry.getKey())
                .collect(joining(" "));
        System.out.println("딜러: " + result);
    }

    public static void printPersonResult(String personName, GameResult gameResult) {
        System.out.println(personName + ": " + gameResult.getName());
    }

    public static void printExceptionMessage(String message) {
        System.out.println(message);
    }
}
