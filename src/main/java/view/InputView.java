package view;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final int IGNORE_EMPTY_INPUT = -1;
    private static final String HIT_CHARACTER = "y";
    private static final String COMMA_DELIMITER = ",";
    private static final String ERROR_DUPLICATE_NAME = "[ERROR] 이름은 중복될 수 없습니다.";
    private static final String INPUT_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_NEED_MORE_CARD = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n";
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> scanPlayerNames() {
        System.out.println(INPUT_PLAYER_NAMES);
        List<String> playerNames = scanRawPlayerNames();
        validateDuplicateNames(playerNames);
        return playerNames;
    }

    private static List<String> scanRawPlayerNames() {
        return Arrays.stream(SCANNER.nextLine()
                        .split(COMMA_DELIMITER, IGNORE_EMPTY_INPUT))
                .map(String::trim)
                .collect(toList());
    }

    public static void validateDuplicateNames(List<String> names) {
        boolean isDuplicate = names.size() != names.stream()
                .distinct()
                .count();

        if (isDuplicate) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NAME);
        }
    }

    public static boolean scanIsHit(String name) {
        System.out.printf(INPUT_NEED_MORE_CARD, name);
        return HIT_CHARACTER.equalsIgnoreCase(SCANNER.nextLine().trim());
    }
}
