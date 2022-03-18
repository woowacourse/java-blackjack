package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String INPUT_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_PLAYER_BETTING = "%s의 베팅 금액은?%n";
    private static final String NAME_DELIMITER = ",";

    private final static Scanner SCANNER = new Scanner(System.in);

    public static List<String> inputPlayerNames() {
        System.out.println(INPUT_PLAYER_NAMES);
        final String names = SCANNER.nextLine();
        System.out.println();
        return splitNames(names);
    }

    private static List<String> splitNames(final String names) {
        return Arrays.stream(names.split(NAME_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toUnmodifiableList());
    }

    public static int inputMoney(final String name) {
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
}
