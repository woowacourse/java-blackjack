package blackjack.view;

import blackjack.dto.BlackjackResult;
import blackjack.dto.DealerResult;
import blackjack.util.Constants;
import java.util.List;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String CARD_DISTRIBUTE = NEW_LINE + "딜러와 %s에게 2장을 나누었습니다." + NEW_LINE;
    private static final String DEALER_CARD_STATUS = "딜러: %s" + NEW_LINE;
    private static final String CARD_STATUS = "%s: %s" + NEW_LINE;
    private static final String DEALER_ADDED_CARD = NEW_LINE + "딜러는 %d이하라 한장의 카드를 더 받았습니다." + NEW_LINE + NEW_LINE;
    private static final String RESULT_CARDS_STATUS = "%s카드: %s - 결과: %d" + NEW_LINE;
    private static final String DEALER_RESULT_CARDS_STATUS = "딜러 카드: %s - 결과: %d" + NEW_LINE;
    private static final String FINAL_RESULT_TITLE = NEW_LINE + "## 최종 승패";
    private static final String DEALER_FINAL_RESULT = "딜러: %d승 %d패 %d무" + NEW_LINE;
    private static final String INVALID_CHOICE_EXCEPTION = "%s 또는 %s을 입력해주세요.";
    private static final String YES_CHOICE = "y";
    private static final String NO_CHOICE = "n";
    public static final String NAME_DELIMITER = ", ";

    private static final OutputView instance = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return instance;
    }

    public void printCardDistribute(final List<String> playerNames) {
        System.out.printf(CARD_DISTRIBUTE, makeCommaString(playerNames));
    }

    public void printHandCards(final String card) {
        System.out.printf(DEALER_CARD_STATUS, card);
    }

    public void printHandCards(final String playerName, final List<String> card) {
        System.out.printf(CARD_STATUS, playerName + "카드", makeCommaString(card));
    }

    public void printAddDealerCard() {
        System.out.printf(DEALER_ADDED_CARD, Constants.DEALER_BOUND);
    }

    public void printCardResultStatus(final List<String> card, final long score) {
        System.out.printf(DEALER_RESULT_CARDS_STATUS, makeCommaString(card), score);
    }

    public void printCardResultStatus(final String name, final List<String> card, final long score) {
        System.out.printf(RESULT_CARDS_STATUS, name, makeCommaString(card), score);
    }

    private String makeCommaString(final List<String> strings) {
        return String.join(NAME_DELIMITER, strings);
    }

    public void printFinalResult(final List<String> names, final BlackjackResult blackjackResult) {
        System.out.println(FINAL_RESULT_TITLE);

        printDealerFinalResult(blackjackResult.getDealerResult());
        for (String name : names) {
            System.out.printf(CARD_STATUS, name, ResultView.toResultView(blackjackResult.findPlayerResultByName(name)));
        }
    }

    private void printDealerFinalResult(final DealerResult dealerResult) {
        System.out.printf(
                DEALER_FINAL_RESULT, dealerResult.getWins(), dealerResult.getLoses(), dealerResult.getDraws()
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
