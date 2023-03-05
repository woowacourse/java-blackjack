package view;

import java.util.List;
import java.util.Map;

public class ResultView {

    public static final String JOIN_DELIMITER = ", ";
    private static final String DEALER_NAME = "딜러";
    private static final int OPEN_CARD_INDEX = 0;

    public static void printInitMessage(List<String> playerNames) {
        String names = String.join(JOIN_DELIMITER, playerNames);
        System.out.println("\n딜러와 " + names + "에게 2장을 나누었습니다.");
    }

    public static void printParticipantResult(Map<String, List<String>> cardNameByParticipantName) {
        for (String participantName : cardNameByParticipantName.keySet()) {
            List<String> cardNames = cardNameByParticipantName.get(participantName);
            printResultByParticipantName(participantName, cardNames);
        }
    }

    private static void printResultByParticipantName(String participantName, List<String> cardNames) {
        if (!participantName.equals(DEALER_NAME)) {
            System.out.println(participantName + "카드: " + String.join(JOIN_DELIMITER, cardNames));
        }
        if (participantName.equals(DEALER_NAME)) {
            System.out.println(participantName + ": " + cardNames.get(OPEN_CARD_INDEX));
        }
    }

    public static void printParticipantFinalResult(String name, List<String> cardName, int totalValueSum) {
        System.out.println(name + "카드: " + String.join(JOIN_DELIMITER, cardName) + " - 결과: " + totalValueSum);
    }

    public static void printFinalFightResult(List<String> finalFightResults) {
        System.out.println("\n## 최종 승패");
        for (String result : finalFightResults) {
            System.out.println(result);
        }
    }
}
