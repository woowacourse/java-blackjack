package view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String NAME_SEPARATOR = ",";

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String rawNames = scanner.nextLine().trim();
        validateBlank(rawNames);
        validateSeparators(rawNames);
        List<String> players = List.of(rawNames.split(NAME_SEPARATOR));
        System.out.println();
        return players;
    }

    private void validateBlank(final String rawNames) {
        if (rawNames == null || rawNames.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력입니다.");
        }
    }

    private void validateSeparators(final String rawNames) {
        if (isInvalidSeparator(rawNames)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 구분자입니다.");
        }
    }

    private boolean isInvalidSeparator(final String rawNames) {
        if (rawNames.startsWith(NAME_SEPARATOR)) {
            return true;
        }
        if (rawNames.endsWith(NAME_SEPARATOR)) {
            return true;
        }
        return rawNames.contains(NAME_SEPARATOR.repeat(2));
    }
}
