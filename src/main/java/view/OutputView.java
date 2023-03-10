package view;

public class OutputView {

    public static final String INPUT_PLAYER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    public static final String INPUT_RECEIVE_YES_OR_NOT_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String BETTING_MONEY_MESSAGE = "의 배팅 금액은?";
    public static final String DEALER_RECEIVED_MESSAGE = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.";

    public static void printInputPlayerNameMessage() {
        printMessage(INPUT_PLAYER_NAME_MESSAGE);
    }

    public static void printInputReceiveYesOrNotMessage(String playerName) {
        printMessage(playerName + INPUT_RECEIVE_YES_OR_NOT_MESSAGE);
    }

    public static void printInputBettingMoney(String playerName) {
        printMessage(playerName + BETTING_MONEY_MESSAGE);
    }

    public static void printDealerReceivedMessage() {
        printMessage(DEALER_RECEIVED_MESSAGE);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
