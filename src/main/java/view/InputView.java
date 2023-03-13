package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String READ_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String READ_ADD_CARD_COMMAND_MESSAGE =
            "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" + System.lineSeparator();
    private static final String SPLIT_DELIMITER = ",";

    private static final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayerNames() {
        System.out.println(READ_PLAYER_NAMES_MESSAGE);
        return splitName(scanner.nextLine());
    }

    private List<String> splitName(String names) {
        return Arrays.asList(names.split(SPLIT_DELIMITER));
    }

    public String readCommand(String name) {
        System.out.println();
        System.out.printf(READ_ADD_CARD_COMMAND_MESSAGE, name);
        return scanner.nextLine();
    }

    public int readBetMoney() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            throw new IllegalArgumentException("숫자만 입력해주세요.");
        }
    }
}
