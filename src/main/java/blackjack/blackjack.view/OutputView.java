package blackjack.blackjack.view;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String INITIAL_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.%n";

    public void print(List<String> playerNames) {
        final String joinNames = String.join(", ", playerNames);

        System.out.printf(INITIAL_MESSAGE, joinNames);
    }
}
