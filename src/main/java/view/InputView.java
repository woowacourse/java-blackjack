package view;

import constant.ErrorMessage;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String NAME_SPLIT_REGEX = ",";

    private final static String NAME_REQUEST_TEXT = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private final static String DRAW_REQUEST_TEXT = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니요는 n)";
    private final static String BET_REQUEST_TEXT = "의 배팅 금액은?";

    private final static Scanner scanner = new Scanner(System.in);

    public static List<String> getPlayerNames() {
        System.out.println(NAME_REQUEST_TEXT);
        String input = getInput();
        System.out.println();
        return Arrays.stream(input.split(NAME_SPLIT_REGEX, -1))
                .toList();
    }

    public static String getDrawCondition(String name) {
        System.out.println(name + DRAW_REQUEST_TEXT);
        return getInput();
    }

    public static Integer getBet(String name) {
        System.out.println(name + BET_REQUEST_TEXT);

        try {
            Integer value = Integer.parseInt(name);
            System.out.println();
            return value;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_BET_INPUT.getMessage());
        }
    }

    private static String getInput() {
        String input = scanner.nextLine();

        if(input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_IS_BLANK.getMessage());
        }
        return input;
    }
}
