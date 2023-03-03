package blackjackgame.view;

import java.util.List;
import java.util.Map;

public class OutputView {
    public static final String FINAL_RESULT_MSG = "## 최종 승패";
    private static final String DELIMITER = ", ";
    private static final String DEALER_HIT_CARD_MSG = "딜러는 16이하라 한장의 카드를 더 받았습니다.";

    public void printFirstDealerCards(final String playerName, final List<List<String>> cards) {
        List<String> card = cards.get(1);
        System.out.printf("%s%s: %s", System.lineSeparator(), playerName, String.join("", card));
    }

    public void printCards(final String playerName, final List<List<String>> cards) {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.printf("%s%s: ", System.lineSeparator(), playerName);
        for (final List<String> card : cards) {
            stringBuilder.append(String.join("", card))
                .append(DELIMITER);
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(DELIMITER));
        System.out.print(stringBuilder);
    }

    public void dealerAddCard() {
        System.out.println(System.lineSeparator() + DEALER_HIT_CARD_MSG);
    }

    public void printScore(final int score) {
        System.out.print(" - 결과: " + score);
    }

    public void printResult(final Map<String, Integer> dealerResult, final Map<String, String> result) {
        System.out.println();
        StringBuilder stringBuilder = new StringBuilder(FINAL_RESULT_MSG + System.lineSeparator() + "딜러: ");
        appendDealerResult(dealerResult, stringBuilder);
        appendGuestsResult(result, stringBuilder);
        System.out.println(System.lineSeparator() + stringBuilder);
    }

    private void appendDealerResult(final Map<String, Integer> dealerResult, final StringBuilder stringBuilder) {
        for (final String gameOutcome : dealerResult.keySet()) {
            stringBuilder.append(dealerResult.get(gameOutcome))
                .append(gameOutcome)
                .append(" ");
        }
    }

    private void appendGuestsResult(final Map<String, String> result, final StringBuilder stringBuilder) {
        for (final String name : result.keySet()) {
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(name)
                .append(": ")
                .append(result.get(name));
        }
    }
}
