package view;

import java.util.List;

public final class OutputView {
    private static final String PARTICIPANT_CARD_FORMAT = "%s : %s\n";
    private static final String PARTICIPANT_HAND_SUM = "%s 카드: %s - 결과: %s\n";
    private static final String INIT_FINISHIED_MESSAGE = "딜러와 %s에게 %d장을 나누었습니다.\n";
    private static final String DEALER_HIT_MESSAGE = "딜러는 16이하라 카드를 더 받았습니다.";
    private static final String RESULT_FORMAT = ": ";
    private static final String RESULT_TAG = "## 최종 수익";
    private static final String DELIMITER = ", ";
    private static final String BLACKJACK = "블랙잭";
    private static final String BUST = "버스트";
    private static final String PLAYER_IS_BLACKJACK = "%s는 블랙잭입니다! 축하합니다!🎉\n";
    private static final String PLAYER_IS_BUST = "이런, %s는 버스트!😭\n";
    private static final int BLACKJACK_HAND_VALUE = -1;
    private static final int BUST_HAND_VALUE = 0;

    public static void printDealFinishMessage(List<String> participantNames, int initHandCount) {
        printEmptyLine();
        String participants = String.join(DELIMITER, participantNames);
        System.out.printf(INIT_FINISHIED_MESSAGE, participants, initHandCount);
    }

    public static void printParticipantCard(String name, List<String> participantsHand) {
        String cards = String.join(DELIMITER, participantsHand);
        System.out.printf((PARTICIPANT_CARD_FORMAT), name, cards);
    }

    public static void printDealerPickCardMessage() {
        printEmptyLine();
        System.out.println(DEALER_HIT_MESSAGE);
        printEmptyLine();
    }

    public static void printParticipantHandValue(String name, List<String> hand, int handValue) {
        String cards = String.join(DELIMITER, hand);
        String value = decideValue(handValue);
        System.out.printf((PARTICIPANT_HAND_SUM), name, cards, value);
    }

    private static String decideValue(int handValue) {
        String value = String.valueOf(handValue);
        if (handValue == BLACKJACK_HAND_VALUE) {
            value = BLACKJACK;
        }
        if (handValue == BUST_HAND_VALUE) {
            value = BUST;
        }
        return value;
    }

    public static void printPlayerHasBlackJack(String playerName) {
        System.out.printf(PLAYER_IS_BLACKJACK, playerName);
    }

    public static void printPlayerIsBust(String playerName) {
        System.out.printf(PLAYER_IS_BUST, playerName);
    }

    public static void printResultInfo() {
        printEmptyLine();
        System.out.println(RESULT_TAG);
    }

    public static void printResult(String name, int profit) {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(stringBuilder.append(name).append(RESULT_FORMAT).append(profit));
    }

    public static void printEmptyLine() {
        System.out.println();
    }

    public static void printExceptionMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
