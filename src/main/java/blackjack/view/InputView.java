package blackjack.view;

import blackjack.domain.betting.BettingMoney;
import java.util.Arrays;
import java.util.List;

public class InputView {

    private static final String DELIMITER = ",";

    private static final String READ_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

    private static final String READ_PLAYER_BETTING_MONEY_MESSAGE = "%n%s의 배팅 금액은?%n";
    private static final String INVALID_BETTING_AMOUNT_MESSAGE = "배팅 금액은 숫자여야 합니다.";

    private static final String READ_HIT_DECISION_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n";
    private static final String HIT_INPUT = "y";
    private static final String STAY_INPUT = "n";
    private static final String INVALID_HIT_DECISION_MESSAGE = "y 또는 n만 입력할 수 있습니다.";

    private InputView() {}

    public static List<String> readPlayerNames() {
        System.out.println(READ_PLAYER_NAMES_MESSAGE);
        return Arrays.stream(ConsoleInput.readLine().split(DELIMITER))
                .map(String::strip)
                .toList();
    }

    public static List<BettingMoney> readBettingMonies(final List<String> names) {
        return names.stream()
                .map(name -> ConsoleInput.readWithRetry(() -> {
                    System.out.printf(READ_PLAYER_BETTING_MONEY_MESSAGE, name);
                    return new BettingMoney(parseBettingAmount(ConsoleInput.readLine()));
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
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_BETTING_AMOUNT_MESSAGE);
        }
    }

    private static void validateHitDecision(final String input) {
        if (!input.equals(HIT_INPUT) && !input.equals(STAY_INPUT)) {
            throw new IllegalArgumentException(INVALID_HIT_DECISION_MESSAGE);
        }
    }
}
