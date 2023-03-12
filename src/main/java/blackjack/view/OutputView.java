package blackjack.view;

import java.util.List;

public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String INIT_FORMAT = LINE_SEPARATOR
            + "딜러와 %s에게 2장을 나누었습니다." + LINE_SEPARATOR;
    private static final String DEALER_CARDS_FORMAT = "%s: %s" + LINE_SEPARATOR;
    private static final String PLAYER_CARDS_FORMAT = "%s카드: %s" + LINE_SEPARATOR;
    private static final String CARDS_RESULT_FORMAT = "%s카드: %s - 결과: %d" + LINE_SEPARATOR;
    private static final String DEALER_HIT_MESSAGE =
            LINE_SEPARATOR + "딜러는 16이하라 한장의 카드를 더 받았습니다." + LINE_SEPARATOR;
    private static final String RESULT_MESSAGE = LINE_SEPARATOR + "## 최종 승패";
    private static final String PARTICIPANT_RESULT_FORMAT = "%s: %d" + LINE_SEPARATOR;

    public void printInitCardsMessage(List<String> participantNames) {
        System.out.printf(INIT_FORMAT, String.join(", ", participantNames));
    }

    public void printDealerInitCards(String name, String cardName) {
        System.out.printf(DEALER_CARDS_FORMAT, name, cardName);
    }

    public void printPlayerCards(String name, List<String> cardNames) {
        System.out.printf(PLAYER_CARDS_FORMAT, name, String.join(", ", cardNames));
    }

    public void printDealerState() {
        System.out.println(DEALER_HIT_MESSAGE);
    }

    public void printEachParticipantCardsResult(String name, List<String> cardNames, int score) {
        System.out.printf(CARDS_RESULT_FORMAT, name, String.join(", ", cardNames), score);
    }

    public void printGameResultMessage() {
        System.out.println(RESULT_MESSAGE);
    }

    public void printDealerGameResult(String name, int amount) {
        System.out.printf(PARTICIPANT_RESULT_FORMAT, name, amount);
    }

    public void printEachPlayerGameResult(String name, int amount) {
        System.out.printf(PARTICIPANT_RESULT_FORMAT, name, amount);
    }

    public void printException(String message) {
        System.out.println(message);
    }
}
