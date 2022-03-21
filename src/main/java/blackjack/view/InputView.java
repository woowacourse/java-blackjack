package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    public static final String INPUT_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    public static final String NAME_SPLIT_DELIMITER = ",";
    public static final String HIT_OR_STAND_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    private static final String INPUT_BETTING_MONEY_MESSAGE = "%s의 베팅 금액은?";
    private static final String CAN_NOT_FIND_INPUT_ERROR_MESSAGE = "입력이 확인되지 않습니다.";
    private static final String NUMBER_FORMAT_ERROR_MESSAGE = "숫자를 입력해주세요.";

    private InputView() {
    }

    public static List<String> inputPlayerNames() {
        System.out.println(INPUT_NAME_MESSAGE);
        return Arrays.stream(readLine().split(NAME_SPLIT_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static int inputBettingMoney(String name) {
        System.out.println();
        System.out.printf(INPUT_BETTING_MONEY_MESSAGE + "\n", name);
        return readIntLine();
    }

    public static String inputHitOrStand(String name) {
        System.out.println();
        System.out.printf(HIT_OR_STAND_MESSAGE, name);
        return readLine();
    }

    private static String readLine() {
        String input = scanner.nextLine();
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(CAN_NOT_FIND_INPUT_ERROR_MESSAGE);
        }
        return input;
    }

    private static int readIntLine() {
        try {
            return Integer.parseInt(readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMBER_FORMAT_ERROR_MESSAGE);
        }
    }
}
