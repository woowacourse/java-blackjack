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
    private static final String CARD_OR_NAME_DELIMITER = ", ";
    private static final String INIT_CARD_SUFFIX_MESSAGE = "에게 2장을 나누었습니다 *^^*";
    private static final String NAME_SUFFIX = "카드: ";
    private static final String SCORE_PREFIX = " - 결과: ";
    private static final String BLANK = " ";
    private static final String NAME_AND_GAME_RESULT_DELIMITER = ": ";

    private OutputView() {
    }

    public static void printInitCardsResult(final Map<String, List<Card>> cardsWithName) {
        printInitCardsIntro(cardsWithName.keySet());
        printCardsWithName(cardsWithName);
    }

    private static void printInitCardsIntro(final Set<String> names) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(String.join(CARD_OR_NAME_DELIMITER, names))
                .append(INIT_CARD_SUFFIX_MESSAGE);
        print(stringBuilder.toString());
    }

    public static void printCardsWithName(final Map<String, List<Card>> cardsWithName, final int... score) {
        for (final Entry<String, List<Card>> entry : cardsWithName.entrySet()) {
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder
                    .append(entry.getKey())
                    .append(NAME_SUFFIX)
                    .append(convertToString(entry.getValue()));
            addScore(stringBuilder, score);
            print(stringBuilder.toString());
        }
    }

    private static void addScore(final StringBuilder stringBuilder, final int[] score) {
        if (score.length != 0) {
            stringBuilder
                    .append(SCORE_PREFIX)
                    .append(score[0]);
        }
    }

    private static String convertToString(final List<Card> cards) {
        return String.join(CARD_OR_NAME_DELIMITER, CardConvertor.convertToString(cards));
    }

    private static void print(final String content) {
        System.out.println(content);
    }

    public static void printDealerHit() {
        print(DEALER_HIT_MESSAGE);
    }

    public static void printCardsWithTotalScore(final CardsWithTotalScore cardsWithTotalScore) {
        for (final Entry<Map<String, List<Card>>, Integer> entry : cardsWithTotalScore.get().entrySet()) {
            printCardsWithName(entry.getKey(), entry.getValue());
        }
    }

    public static void printGameResultWithName(final Map<String, List<String>> gameResult) {
        print(GAME_RESULT_INTRO_MESSAGE);
        for (final Entry<String, List<String>> entry : gameResult.entrySet()) {
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder
                    .append(entry.getKey())
                    .append(NAME_AND_GAME_RESULT_DELIMITER)
                    .append(String.join(BLANK, entry.getValue()));
            print(stringBuilder.toString());
        }
    }
}
