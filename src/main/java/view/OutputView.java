package view;

import domain.card.Card;
import domain.game.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {

    public static void printDealerCardWithHidden(final Card card) {
        System.out.println("딜러: " + getCardName(card));
    }

    public static void printPlayerCards(final Map<String, List<String>> playerToCard) {
        playerToCard.forEach((playerName, cards) ->
                System.out.println(getEachPlayerCards(playerName, cards)));
    }

    public static void printDealerCardWithScore(final List<String> cards, final int score) {
        printCardWithScore("딜러 ", cards, score);
    }

    public static void printPlayerCardWithScore(final Map<String, List<String>> playerToCard,
                                                final Map<String, Integer> playerToScore) {
        playerToCard.forEach((playerName, cards) -> {
            int score = playerToScore.get(playerName);
            printCardWithScore(playerName, cards, score);
        });
    }

    public static void printEachPlayerCards(final String playerName, final List<String> cards) {
        System.out.println(getEachPlayerCards(playerName, cards));
    }

    private static void printCardWithScore(final String playerName, final List<String> cards, final int score) {
        System.out.println(getEachPlayerCards(playerName, cards) + " - 결과: " + score);
    }

    private static String getEachPlayerCards(final String playerName, final List<String> cardNames) {
        StringBuilder stringBuilder = new StringBuilder(playerName);
        stringBuilder.append("카드: ");
        stringBuilder.append(String.join(", ", cardNames));
        return stringBuilder.toString();
    }

    private static String getCardName(final Card card) {
        return card.getCardName();
    }

    public static void printGameResult(final Map<Result, Integer> dealerResult,
                                       final Map<String, Result> playerResults) {
        System.out.println();
        System.out.println("## 최종 승패");
        printDealerResult(dealerResult);
        printPlayerResults(playerResults);
    }

    private static void printDealerResult(final Map<Result, Integer> dealerResult) {
        StringBuilder dealerResultMessage = new StringBuilder("딜러: ");
        for (Result result : dealerResult.keySet()) {
            int count = dealerResult.getOrDefault(result, 0);
            dealerResultMessage.append(getDealerEachResult(result.getName(), count));
        }
        System.out.println(dealerResultMessage);
    }

    private static String getDealerEachResult(final String gameResultName, final int count) {
        if (count != 0) {
            return count + gameResultName + " ";
        }
        return "";
    }

    private static void printPlayerResults(final Map<String, Result> playerResults) {
        playerResults.forEach((playerName, gameResult) ->
                System.out.println(playerName + ": " + gameResult.getName()));
    }

    public static void printDealerHitMessage() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printInitMessage(List<String> playerNames) {
        System.out.println();
        System.out.println("딜러와 " + String.join(", ", playerNames) + "에게 2장을 나누었습니다.");
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }
}
