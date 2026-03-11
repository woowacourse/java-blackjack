package blackjack.view;

import java.util.Arrays;
import java.util.List;

public class InputView {

    private static final String DELIMITER = ",";

    private static final String READ_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String EMPTY_NAMES_MESSAGE = "이름을 1개 이상 입력해야 합니다.";
    private static final String BLANK_NAME_MESSAGE = "이름은 공백일 수 없습니다.";
    private static final String DUPLICATE_NAME_MESSAGE = "중복된 이름은 입력할 수 없습니다.";

    private static final String READ_PLAYER_BETTING_MONEY_MESSAGE = "%n%s의 배팅 금액은?%n";
    private static final String INVALID_BETTING_AMOUNT_MESSAGE = "배팅 금액은 숫자여야 합니다.";

    private static final String READ_HIT_DECISION_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n";
    private static final String HIT_INPUT = "y";
    private static final String STAY_INPUT = "n";
    private static final String INVALID_HIT_DECISION_MESSAGE = "y 또는 n만 입력할 수 있습니다.";

    private InputView() {}

    public static List<String> readPlayerNames() {
        return ConsoleInput.readWithRetry(() -> {
            System.out.println(READ_PLAYER_NAMES_MESSAGE);
            final List<String> names = Arrays.stream(ConsoleInput.readLine().split(DELIMITER))
                    .map(String::strip)
                    .toList();
            validatePlayerNames(names);
            return names;
        });
    }

    public static List<Integer> readBettingAmounts(final List<String> names) {
        return names.stream()
                .map(name -> ConsoleInput.readWithRetry(() -> {
                    System.out.printf(READ_PLAYER_BETTING_MONEY_MESSAGE, name);
                    return parseBettingAmount(ConsoleInput.readLine());
                }))
                .toList();
    }

    public static boolean readHitDecision(final String name) {
        return ConsoleInput.readWithRetry(() -> {
            System.out.printf(READ_HIT_DECISION_MESSAGE, name);
            final String input = ConsoleInput.readLine();
            validateHitDecision(input);
            return input.equals(HIT_INPUT);
        });
    }

    private static int parseBettingAmount(final String input) {
        try {
            final int amount = Integer.parseInt(input);
            if (amount <= 0) {
                throw new IllegalArgumentException(INVALID_BETTING_AMOUNT_MESSAGE);
            }
            return amount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_BETTING_AMOUNT_MESSAGE);
        }
    }

    private static void validatePlayerNames(final List<String> names) {
        if (names.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_NAMES_MESSAGE);
        }
        if (names.stream().anyMatch(String::isBlank)) {
            throw new IllegalArgumentException(BLANK_NAME_MESSAGE);
        }
        if (names.size() != names.stream().distinct().count()) {
            throw new IllegalArgumentException(DUPLICATE_NAME_MESSAGE);
        }
    }

    private static void validateHitDecision(final String input) {
        if (!input.equals(HIT_INPUT) && !input.equals(STAY_INPUT)) {
            throw new IllegalArgumentException(INVALID_HIT_DECISION_MESSAGE);
        }
    }
}
