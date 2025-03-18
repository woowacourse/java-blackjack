package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

public class Input {
    private static final String ERROR_HEADER = "[ERROR] ";
    private static final String WARNING_EMPTY_STRING = "빈 문자열은 이름이 될 수 없습니다.";
    private static final String WARING_INVALID_NUMBER = "정수형 숫자만 입력 가능합니다.";
    private static final String WARNING_MINIMUM_NUMBER = "0 이상의 숫자만 입력 가능합니다.";
    private static final String WARNING_INVALID_OPTION = "올바른 형식의 옵선이 입력되지 않았습니다.";

    private static final String DELIMITER = ",";
    private static final String OPTION_POSITIVE = "y";
    private static final String OPTION_NEGATIVE = "n";
    private static final int MINIMUM_WAGER = 0;

    private final Scanner scanner = new Scanner(System.in);

    public <T> T repeatInput(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                displayError(e.getMessage());
            }
        }
    }

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return repeatInput(() -> {
            String names = scanner.nextLine();
            validateNames(names);
            return splitNames(names);
        });
    }

    private void validateNames(final String names) {
        if (names == null || names.isBlank()) {
            throw new IllegalArgumentException(WARNING_EMPTY_STRING);
        }
    }

    private List<String> splitNames(final String names) {
        return Arrays.stream(names.split(DELIMITER)).map(String::trim).toList();
    }

    public int readWager(final String playerName) {
        return repeatInput(() -> {
            System.out.println();
            System.out.println(playerName + "의 배팅 금액은?");
            String wager = scanner.nextLine();
            validateWager(wager);
            return Integer.parseInt(wager);
        });
    }

    private void validateWager(final String wager) {
        try {
            Integer.parseInt(wager);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(WARING_INVALID_NUMBER);
        }
        if (Integer.parseInt(wager) <= MINIMUM_WAGER) {
            throw new IllegalArgumentException(WARNING_MINIMUM_NUMBER);
        }
    }

    public String readHitOption(final String playerName) {
        System.out.println();
        return repeatInput(() -> {
            System.out.println(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
            String option = scanner.nextLine();
            validateOption(option);
            return option;
        });
    }

    private void validateOption(final String option) {
        if (!OPTION_POSITIVE.equals(option) && !OPTION_NEGATIVE.equals(option)) {
            throw new IllegalArgumentException(WARNING_INVALID_OPTION);
        }
    }

    private void displayError(String message) {
        System.out.println(ERROR_HEADER + message);
    }
}
