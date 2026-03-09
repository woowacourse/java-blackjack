package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String DELIMITER = ",";
    private static final String HIT_INPUT = "y";
    private static final String STAY_INPUT = "n";
    private static final String READ_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String READ_HIT_DECISION_FORMAT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n";
    private static final String INVALID_HIT_DECISION_MESSAGE = "y 또는 n만 입력할 수 있습니다.";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayerNames() {
        System.out.println(READ_PLAYER_NAMES_MESSAGE);
        String input = scanner.nextLine();
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .toList();
    }

    public boolean readHitDecision(final String name) {
        System.out.printf(READ_HIT_DECISION_FORMAT, name);
        String input = scanner.nextLine().trim();
        validateHitDecision(input);
        return input.equals(HIT_INPUT);
    }

    private void validateHitDecision(final String input) {
        if (!input.equals(HIT_INPUT) && !input.equals(STAY_INPUT)) {
            throw new IllegalArgumentException(INVALID_HIT_DECISION_MESSAGE);
        }
    }
}
