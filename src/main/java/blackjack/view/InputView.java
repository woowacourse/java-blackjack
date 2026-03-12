package blackjack.view;

import blackjack.view.validator.InputFormatValidator;
import java.util.Arrays;
import java.util.List;

public class InputView {

    private static final String DELIMITER = ",";

    private static final String READ_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String READ_PLAYER_BETTING_MONEY_MESSAGE = "%n%s의 배팅 금액은?%n";
    private static final String READ_HIT_DECISION_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n";

    private static final String HIT_INPUT = "y";

    private InputView() {}

    public static List<String> readPlayerNames() {
        return ConsoleInput.readWithRetry(() -> {
            System.out.println(READ_PLAYER_NAMES_MESSAGE);
            final List<String> names = Arrays.stream(ConsoleInput.readLine().split(DELIMITER))
                    .map(String::strip)
                    .toList();
            InputFormatValidator.validateNamesFormat(names);
            return names;
        });
    }

    public static List<Integer> readBettingAmounts(final List<String> names) {
        return names.stream()
                .map(name -> ConsoleInput.readWithRetry(() -> {
                    System.out.printf(READ_PLAYER_BETTING_MONEY_MESSAGE, name);
                    return InputFormatValidator.parseToInt(ConsoleInput.readLine());
                }))
                .toList();
    }

    public static boolean readHitDecision(final String name) {
        return ConsoleInput.readWithRetry(() -> {
            System.out.printf(READ_HIT_DECISION_MESSAGE, name);
            final String input = ConsoleInput.readLine();

            InputFormatValidator.validateHitDecision(input);
            return input.equals(HIT_INPUT);
        });
    }
}
