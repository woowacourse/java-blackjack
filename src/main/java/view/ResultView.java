package view;

import java.util.List;

public class ResultView {

    public static final String JOIN_DELIMITER = ", ";

    public static void printInitMessage(List<String> playerNames) {
        String names = String.join(JOIN_DELIMITER, playerNames);
        System.out.println("\n딜러와 " + names + "에게 2장을 나누었습니다.");
    }

    public static void printParticipantResult(String name, List<String> cardName) {
        String result = ": " + String.join(JOIN_DELIMITER, cardName);
        if (!name.equals("딜러")) {
            result = "카드" + result;
        }
        System.out.println(name + result);
    }
}
