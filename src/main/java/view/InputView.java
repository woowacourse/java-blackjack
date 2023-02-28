package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String DELIMITER = ",";

    public List<String> requestPlayerNames() {
        System.out.println(Message.PLAYER_NAMES_INPUT_MESSAGE.message);
        String playerNames = SCANNER.nextLine();

        return Arrays.stream(playerNames.split(DELIMITER))
                .collect(Collectors.toList());
    }
    private enum Message {
        PLAYER_NAMES_INPUT_MESSAGE("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"),
        ;
        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}
