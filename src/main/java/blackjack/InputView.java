package blackjack;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String NAME_DELIMITER = ",";
    private static final String YES = "y";
    private static final String NO = "n";

    private static final Scanner SCANNER = new Scanner(System.in);

    public static List<String> inputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return toNames(SCANNER.nextLine().trim());
    }

    private static List<String> toNames(String inputNames) {
        return Arrays.stream(inputNames.split(NAME_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static String inputCommand(Player player) {
        System.out.println(MessageFormat.format("{0}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", player.getName()));
        return validateCommand(SCANNER.nextLine().trim().toLowerCase());
    }

    private static String validateCommand(String value) {
        if (!YES.equals(value) && !NO.equals(value)) {
            throw new IllegalArgumentException("입력은 y 또는 n이어야 합니다.");
        }
        return value;
    }
}
