package view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_PLAYER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
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

    public static boolean inputReceiveOrNot() {
        return ReceiveValidate.checkReceivable(SCANNER.nextLine());
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
