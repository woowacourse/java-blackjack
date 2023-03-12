package blackjack.view;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String INITIAL_STATUS_INFO_MESSAGE_FORMAT = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String CARD_INFO_MESSAGE_FORMAT = "%s카드: %s";
    private static final String DEALER_DRAW_INFO_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
            + System.lineSeparator();
    private static final String USER_NAME_AND_CARD_RESULT_MESSAGE_FORMAT = "%s카드: %s";
    private static final String PROFIT_RESULT_INFO_MESSAGE = "## 최종 수익";
    private static final String USER_PROFIT_RESULT_MESSAGE_FORMAT = "%s: %d";
    private static final String DELIMITER = ", ";

    public void printFirstCardGroupInfoMessage(final List<String> playerNames) {
        printLineBreak();
        System.out.println(String.format(INITIAL_STATUS_INFO_MESSAGE_FORMAT
                , String.join(DELIMITER, playerNames)));
    }

    public void printUserNameAndCardGroup(String name, List<String> cardNames) {
        System.out.println(String.format(CARD_INFO_MESSAGE_FORMAT, name
                , String.join(DELIMITER, cardNames)));
    }

    public void printDealerDrawInfoMessage() {
        System.out.println(DEALER_DRAW_INFO_MESSAGE);
    }

    public void printUserNameAndCardResults(final Map<String, String> renderedUserNameAndCardResults) {
        renderedUserNameAndCardResults.forEach((name, renderedCardResult) ->
                System.out.println(String.format(USER_NAME_AND_CARD_RESULT_MESSAGE_FORMAT, name, renderedCardResult)));
    }

    public void printUsersProfits(final Map<String, Integer> usersProfits) {
        printLineBreak();
        System.out.println(PROFIT_RESULT_INFO_MESSAGE);
        for (final String userName : usersProfits.keySet()) {
            System.out.println(String.format(USER_PROFIT_RESULT_MESSAGE_FORMAT, userName, usersProfits.get(userName)));
        }
    }

    public void printLineBreak() {
        System.out.println();
    }

    public void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
