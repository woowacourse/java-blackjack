package view;

import exception.ErrorMessage;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String START_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String YN_FORMAT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    private static final String BETTING_AMOUNT_FORMAT = "%s의 배팅 금액은?\n";


    private static Scanner sc = new Scanner(System.in);

    public static List<String> askPlayerNames() {
        System.out.println(START_MESSAGE);
        List<String> inputs = Arrays.asList(sc.nextLine().split(","));

        for (String input : inputs) {
            validateInput(input);
        }
        return inputs;
    }

    private static void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_NAME.getMessage());
        }
    }

    public static int askBettingAmount(String name) {
        System.out.printf(BETTING_AMOUNT_FORMAT, name);
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("배팅금액은 숫자여야 합니다.");
        }
    }

    // todo : COMMAND 원시값 포장
    public static String askPlayerCommand(String name) {
        System.out.printf(YN_FORMAT, name);
        String input = sc.nextLine();
        if (input.equals("y") || input.equals("n")) {
            return input;
        }
        throw new IllegalArgumentException(ErrorMessage.INVALID_YN.getMessage());
    }
}
