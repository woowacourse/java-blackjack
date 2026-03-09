package view;

import constant.ErrorMessage;
import java.util.Scanner;

public class InputView {
    private final static String NAME_REQUEST_TEXT = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private final static String DRAW_REQUEST_TEXT = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니요는 n)";

    private final static Scanner scanner = new Scanner(System.in);

    public static String getPlayerNames() {
        System.out.println(NAME_REQUEST_TEXT);
        return getInput();
    }

    public static String getDrawCondition(String name) {
        System.out.println(name + DRAW_REQUEST_TEXT);
        return getInput();
    }

    private static String getInput() {
        String input = scanner.nextLine();

        if(input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_IS_BLANK.getMessage());
        }
        return input;
    }
}
