package blackjackgame.view;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String DELIMITER = ", ";

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
        System.out.println(System.lineSeparator() + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printScore(final int score) {
        System.out.print(" - 결과: " + score);
    }

    public void printResult(final Map<String, Integer> dealerResult, final Map<String, String> result) {
        System.out.println();
        StringBuilder stringBuilder = new StringBuilder("## 최종 승패" + System.lineSeparator() + "딜러: ");
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
