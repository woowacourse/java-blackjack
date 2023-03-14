package view;

import domain.participant.Name;
import domain.participant.ReceiveValidate;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_PLAYER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_BETTING_AMOUNT_MESSAGE = "의 베팅 금액은?";
    private static final String INPUT_RECEIVE_YES_OR_NOT_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    public static final String INPUT_EMPTY_ERROR_MESSAGE = "[ERROR] 입력값이 존재하지 않습니다.";
    public static final String SPLITTER = ",";

    public static List<String> inputPlayerNames() {
        printMessage(INPUT_PLAYER_NAME_MESSAGE);
        String playerNames = SCANNER.nextLine();
        validateBlank(playerNames);
        return List.of(playerNames.split(SPLITTER, -1));
    }

    public static void validateBlank(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(INPUT_EMPTY_ERROR_MESSAGE);
        }
    }

    public static boolean inputReceiveOrNot(String playerName) {
        printMessage(playerName + INPUT_RECEIVE_YES_OR_NOT_MESSAGE);
        return ReceiveValidate.checkReceivable(SCANNER.nextLine());
    }

    public static String inputBettingAmount(Name name) {
        printMessage("\n" + name.getValue() + INPUT_BETTING_AMOUNT_MESSAGE);
        String input = SCANNER.nextLine();
        validateBlank(input);
        return input;
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
