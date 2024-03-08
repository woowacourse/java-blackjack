package blackjack.view;

import blackjack.dto.BlackjackResult;
import blackjack.dto.DealerResult;
import blackjack.util.Constants;
import java.util.List;

public class OutputView {
    private static final String CARD_DISTRIBUTE = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String DEALER_CARD_STATUS = "딜러: %s";
    private static final String CARD_STATUS = "%s: %s";
    private static final String DEALER_ADDED_CARD = "딜러는 %d이하라 한장의 카드를 더 받았습니다.";
    private static final String RESULT_CARDS_STATUS = "%s카드: %s - 결과: %d";
    private static final String DEALER_RESULT_CARDS_STATUS = "딜러 카드: %s - 결과: %d";
    private static final String FINAL_RESULT_TITLE = "## 최종 승패";
    private static final String DEALER_FINAL_RESULT = "딜러: %d승 %d패 %d무";
    private static final String INVALID_CHOICE_EXCEPTION = "%s 또는 %s을 입력해주세요.";
    private static final String YES_CHOICE = "y";
    private static final String NO_CHOICE = "n";

    private static final OutputView instance = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return instance;
    }

    public void printCardDistribute(final List<String> playerNames) {
        System.out.printf(System.lineSeparator() + CARD_DISTRIBUTE + System.lineSeparator(),
                makeCommaString(playerNames));
    }

    public void printHandCards(final String card) {
        System.out.printf(DEALER_CARD_STATUS + System.lineSeparator(), card);
    }

    public void printHandCards(final String playerName, final List<String> card) {
        System.out.printf(CARD_STATUS + System.lineSeparator(), playerName + "카드", makeCommaString(card));
    }

    public void printAddDealerCard() {
        System.out.printf(System.lineSeparator() + DEALER_ADDED_CARD + System.lineSeparator() + System.lineSeparator(),
                Constants.DEALER_BOUND);
    }

    public void printCardResultStatus(final List<String> card, final long score) {
        System.out.printf(DEALER_RESULT_CARDS_STATUS + System.lineSeparator(), makeCommaString(card), score);
    }

    public void printCardResultStatus(final String name, final List<String> card, final long score) {
        System.out.printf(RESULT_CARDS_STATUS + System.lineSeparator(), name, makeCommaString(card), score);
    }

    private String makeCommaString(final List<String> strings) {
        return String.join(", ", strings);
    }

    public void printFinalResult(final List<String> names, final BlackjackResult blackjackResult) {
        System.out.println(System.lineSeparator() + FINAL_RESULT_TITLE);

        printDealerFinalResult(blackjackResult.getDealerResult());
        for (String name : names) {
            System.out.printf(CARD_STATUS + System.lineSeparator(), name,
                    ResultView.toResultView(blackjackResult.findPlayerResultByName(name)));
        }
    }

    private void printDealerFinalResult(final DealerResult dealerResult) {
        System.out.printf(
                DEALER_FINAL_RESULT + System.lineSeparator(),
                dealerResult.getWins(),
                dealerResult.getLoses(),
                dealerResult.getDraws()
        );
    }

    public boolean isMoreChoice(final String choice) {
        if (!List.of(YES_CHOICE, NO_CHOICE).contains(choice)) {
            throw new IllegalArgumentException(String.format(INVALID_CHOICE_EXCEPTION, YES_CHOICE, NO_CHOICE));
        }

        return choice.equals(YES_CHOICE);
    }

    public void printNewLine() {
        System.out.println();
    }
}
