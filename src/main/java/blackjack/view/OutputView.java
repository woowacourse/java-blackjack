package blackjack.view;

import java.util.List;

public class OutputView {
    private static final String CARD_DISTRIBUTE = "%s와 %s에게 2장을 나누었습니다.";
    private static final String CARD_STATUS = "%s: %s";

    private static final OutputView instance = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return instance;
    }

    public void printCardDistribute(final String dealerName, final List<String> playerNames) {
        System.out.printf(
                System.lineSeparator() + CARD_DISTRIBUTE + System.lineSeparator(),
                dealerName, makeCommaString(playerNames)
        );
    }

    public void printHandCards(final String dealerName, final String card) {
        System.out.printf(CARD_STATUS + System.lineSeparator(), dealerName, card);
    }

    public void printHandCards(final String playerName, final List<String> card) {
        System.out.printf(CARD_STATUS + System.lineSeparator(), playerName + "카드", makeCommaString(card));
    }

    private String makeCommaString(final List<String> strings) {
        return String.join(", ", strings);
    }

}
