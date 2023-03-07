package view;

import java.util.List;

public class OutputView {

    private static final String INPUT_PLAYER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_RECEIVE_YES_OR_NOT_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String DEALER_RECEIVED_MESSAGE = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.";
    public static final String JOIN_DELIMITER = ", ";

    public static void printInputPlayerNameMessage() {
        printMessage(INPUT_PLAYER_NAME_MESSAGE);
    }

    public static void printInputReceiveYesOrNotMessage(String playerName) {
        printMessage(playerName + INPUT_RECEIVE_YES_OR_NOT_MESSAGE);
    }

    public static void printDealerReceivedMessage() {
        printMessage(DEALER_RECEIVED_MESSAGE);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

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
