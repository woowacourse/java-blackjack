package view;

import java.util.List;

public class ResultView {

    public static final String JOIN_DELIMITER = ", ";

    public static void printInitMessage(List<String> playerNames) {
        String names = String.join(JOIN_DELIMITER, playerNames);
        System.out.println("\n딜러와 " + names + "에게 2장을 나누었습니다.");
    }

    public static void printParticipantResult(String name, List<String> cardName) {
        if (!name.equals("딜러")) {
            System.out.println(name + "카드: " + String.join(JOIN_DELIMITER, cardName));
            return;
        }
        System.out.println(name + ": " + cardName.get(0));
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
