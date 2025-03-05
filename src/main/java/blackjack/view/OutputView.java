package blackjack.view;

import java.util.List;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println(ERROR_PREFIX + e.getMessage());
    }

    public void printStartGame(List<String> playerNames) {
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.\n", String.join(", ", playerNames));
    }
}
