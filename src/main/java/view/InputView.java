package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String SPLIT_DELIMITER = ",";
    private static final String YES = "y";
    private static final String NO = "n";

    private InputView() {
    }

    public static List<String> readNames() {
        final String input = scanner.nextLine();
        final List<String> names = splitAsList(input);

        if (names.size() == 0) {
            throw new IllegalArgumentException("[ERROR] 플레이어의 수는 1명이상 이어야합니다.");
        }
        return names;
    }

    private static List<String> splitAsList(final String input) {
        return Arrays.stream(input.split(SPLIT_DELIMITER))
                .map(String::strip)
                .collect(Collectors.toList());
    }

    public static boolean readAnswer() {
        final String input = scanner.nextLine();
        if (input.equals(YES)) {
            return true;
        }
        if (input.equals(NO)) {
            return false;
        }
        throw new IllegalArgumentException("[ERROR] y/n만 입력 가능합니다.");
    }

    public static Integer readAmount() {
        return Integer.parseInt(scanner.nextLine());
    }
}
