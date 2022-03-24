package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String INPUT_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_PLAYER_BETTING = "%s의 베팅 금액은?%n";
    private static final String PLAYER_INPUT_SIGN_FORMAT = "%n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n";
    private static final String NAME_DELIMITER = ",";
    private static final String HIT_SIGN = "y";
    private static final String STAY_SIGN = "n";

    private final static Scanner SCANNER = new Scanner(System.in);

    public static List<String> inputPlayerNames() {
        System.out.println(INPUT_PLAYER_NAMES);
        final List<String> names = split(SCANNER.nextLine());
        checkEmptyIn(names);
        return names;
    }

    private static List<String> split(final String names) {
        return Arrays.stream(names.split(NAME_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toUnmodifiableList());
    }

    private static void checkEmptyIn(List<String> names) {
        for (String name : names) {
            checkEmpty(name);
        }
    }

    private static void checkEmpty(String name) {
        if (name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력된 이름이 없거나 공백인 경우가 있습니다.");
        }
    }

    public static int inputBet(final String name) {
        System.out.printf(INPUT_PLAYER_BETTING, name);
        final String money = SCANNER.nextLine();
        return parseToInt(money);
    }

    private static int parseToInt(final String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException("[ERROR] 입력 금액이 정수가 아닙니다.");
        }
    }

    public static boolean inputSign(final String name) {
        System.out.printf(PLAYER_INPUT_SIGN_FORMAT, name);
        String sign = SCANNER.nextLine();
        return parseToBoolean(sign);
    }

    private static boolean parseToBoolean(String sign) {
        if (sign.equals(STAY_SIGN)) {
            return true;
        }
        if (sign.equals(HIT_SIGN)) {
            return false;
        }
        throw new IllegalArgumentException("[ERROR] y 또는 n만 입력해주세요");
    }
}
