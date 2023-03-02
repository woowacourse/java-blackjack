package view;

import java.util.List;
import java.util.Map;

import domain.Result;

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

    public void printInitializingFinishMessage(List<String> participantNames) {
        printEmptyLine();
        String participants = String.join(DELIMITER, participantNames);
        System.out.printf(INIT_FINISHIED_MESSAGE, participants);
    }

    public void printParticipantCard(String name, List<String> participantsHand) {
        String cards = String.join(DELIMITER, participantsHand);
        System.out.printf((PARTICIPANT_CARD_FORMAT), name, cards);
    }

    public void printDealerPickCardMessage() {
        System.out.println(DEALER_HIT_MESSAGE);
        printEmptyLine();
    }

    public void printParticipantHandValue(String participantName, List<String> participantCards, String handValue) {
        String cards = String.join(DELIMITER, participantCards);
        System.out.printf((PARTICIPANT_HAND_SUM), participantName, cards, handValue);
    }

    public void printEmptyLine() {
        System.out.println();
    }

    public void printDealerResult(Map<Result, Integer> dealerResult) {
        StringBuilder result = new StringBuilder();
        result.append(DEALER_TAG).append(RESULT_FORMAT);
        if (dealerResult.getOrDefault(Result.WIN, 0) > 0) {
            result.append(dealerResult.get(Result.WIN)).append(Result.WIN.getResult()).append(BLANK);
        }
        if (dealerResult.getOrDefault(Result.TIE, 0) > 0) {
            result.append(dealerResult.get(Result.TIE)).append(Result.TIE.getResult()).append(BLANK);
        }
        if (dealerResult.getOrDefault(Result.LOSE, 0) > 0) {
            result.append(dealerResult.get(Result.LOSE)).append(Result.LOSE.getResult()).append(BLANK);
        }

        System.out.println(result);
    }

    public void printPlayerResult(String name, Result result) {
        System.out.println(name + RESULT_FORMAT + result.getResult());
    }

    public void printResultInfo() {
        printEmptyLine();
        System.out.println(RESULT_TAG);
    }

    public void printExceptionMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
