package view;

import java.util.List;
import java.util.Map;

import domain.game.Result;

public class OutputView {
    private static final String PARTICIPANT_CARD_FORMAT = "%s : %s\n";
    private static final String PARTICIPANT_HAND_SUM = "%s 카드: %s - 결과: %s\n";
    private static final String INIT_FINISHIED_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.\n";
    private static final String DEALER_HIT_MESSAGE = "딜러는 16이하라 카드를 더 받았습니다.";
    private static final String DEALER_TAG = "딜러";
    private static final String RESULT_FORMAT = ": ";

    private static final String RESULT_TAG = "## 최종 승패";

    private static final String DELIMITER = ", ";

    private static final String BLANK = " ";

    public static void printInitializingFinishMessage(List<String> participantNames) {
        printEmptyLine();
        String participants = String.join(DELIMITER, participantNames);
        System.out.printf(INIT_FINISHIED_MESSAGE, participants);
    }

    public static void printParticipantCard(String name, List<String> participantsHand) {
        String cards = String.join(DELIMITER, participantsHand);
        System.out.printf((PARTICIPANT_CARD_FORMAT), name, cards);
    }

    public static void printDealerPickCardMessage() {
        System.out.println(DEALER_HIT_MESSAGE);
        printEmptyLine();
    }

    public static void printParticipantHandValue(String participantName, List<String> participantCards,
        String handValue) {
        String cards = String.join(DELIMITER, participantCards);
        System.out.printf((PARTICIPANT_HAND_SUM), participantName, cards, handValue);
    }

    public static void printEmptyLine() {
        System.out.println();
    }

    public static void printDealerResult(Map<Result, Integer> results) {
        StringBuilder dealerResult = new StringBuilder();
        dealerResult.append(DEALER_TAG).append(RESULT_FORMAT);
        countTargetResult(results, Result.WIN, dealerResult);
        countTargetResult(results, Result.TIE, dealerResult);
        countTargetResult(results, Result.LOSE, dealerResult);

        System.out.println(dealerResult);
    }

    private static void countTargetResult(Map<Result, Integer> results, Result result, StringBuilder dealerResult) {
        int count = results.getOrDefault(result, 0);
        if (count > 0) {
            dealerResult.append(results.get(result)).append(result.getResult()).append(BLANK);
        }
    }

    public static void printPlayerResult(String name, Result result) {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(stringBuilder.append(name).append(RESULT_FORMAT).append(result.getResult()));
    }

    public static void printResultInfo() {
        printEmptyLine();
        System.out.println(RESULT_TAG);
    }

    public static void printExceptionMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
