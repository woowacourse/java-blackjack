package view;

import domain.card.Card;
import dto.CardsWithTotalScore;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import utils.CardConvertor;

public class OutputView {
    private static final String DEALER_HIT_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String GAME_RESULT_INTRO_MESSAGE = "## 최종 승패";

    private OutputView() {
    }

    public static void printInitCardsResult(final Map<String, List<Card>> cardsWithName) {
        printInitCardsIntro(cardsWithName.keySet());
        printCardsWithName(cardsWithName);
    }

    private static void printInitCardsIntro(final Set<String> names) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(String.join(", ", names))
                .append("에게 2장을 나누었습니다 *^^*");
        print(stringBuilder.toString());
    }

    public static void printCardsWithName(final Map<String, List<Card>> cardsWithName, final int... score) {
        for (final Entry<String, List<Card>> entry : cardsWithName.entrySet()) {
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder
                    .append(entry.getKey())
                    .append("카드: ")
                    .append(convertToString(entry.getValue()));
            addScore(stringBuilder, score);
            print(stringBuilder.toString());
        }
    }

    private static void addScore(final StringBuilder stringBuilder, final int[] score) {
        if (score.length != 0) {
            stringBuilder
                    .append(" - 결과: ")
                    .append(score[0]);
        }
    }

    private static String convertToString(final List<Card> cards) {
        return String.join(", ", CardConvertor.convertToString(cards));
    }

    private static void print(final String content) {
        System.out.println(content);
    }

    public static void printDealerHit() {
        print(DEALER_HIT_MESSAGE);
    }

    public static void printCardsWithTotalScore(CardsWithTotalScore cardsWithTotalScore) {
        for (Entry<Map<String, List<Card>>, Integer> entry : cardsWithTotalScore.get().entrySet()) {
            printCardsWithName(entry.getKey(), entry.getValue());
        }
    }

    public static void printGameResultWithName(final Map<String, List<String>> gameResult) {
        print(GAME_RESULT_INTRO_MESSAGE);
        for (final Entry<String, List<String>> entry : gameResult.entrySet()) {
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder
                    .append(entry.getKey())
                    .append(": ")
                    .append(String.join(" ", entry.getValue()));
            print(stringBuilder.toString());
        }
    }
}
