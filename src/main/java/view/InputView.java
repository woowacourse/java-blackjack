package view;

import domain.gamer.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = ",";
    private static final String NOT_ALLOWED_DELIMITER_POSITION = String.format("참가자 이름 입력은 %s로 시작하거나, 끝날 수 없습니다.",
            DELIMITER);
    private static final String YES = "y";
    private static final String NO = "n";
    private static final String INVALID_SELECTION = String.format("%s 또는 %s만 입력할 수 있습니다.", YES, NO);

    private InputView() {
    }

    public static List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        validateStartsWithDelimiter(input);
        validateEndsWithDelimiter(input);
        String[] playerNames = input.split(DELIMITER);
        return Arrays.stream(playerNames).map(String::trim).toList();
    }

    private static void validateStartsWithDelimiter(final String input) {
        if (input.startsWith(DELIMITER)) {
            throw new IllegalArgumentException(NOT_ALLOWED_DELIMITER_POSITION);
        }
    }

    private static void validateEndsWithDelimiter(final String input) {
        if (input.endsWith(DELIMITER)) {
            throw new IllegalArgumentException(NOT_ALLOWED_DELIMITER_POSITION);
        }
    }

    public static boolean readSelectionOf(final Player player) {
        String message = String.format(System.lineSeparator() + "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)",
                player.getName().getValue());
        System.out.println(message);
        String input = scanner.nextLine().trim();
        validateInvalidSelection(input);
        return input.equalsIgnoreCase(YES);
    }

    private static void validateInvalidSelection(final String input) {
        if (!input.equalsIgnoreCase(YES) && !input.equalsIgnoreCase(NO)) {
            throw new IllegalArgumentException(INVALID_SELECTION);
        }
    }
}
