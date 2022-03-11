package blackjack.view;

import java.util.List;
import java.util.stream.Collectors;

public class ResultView {
    private static final String MESSAGE_DECK_INITIALIZED = "딜러와 %s에게 2장의 카드를 나누었습니다.";
    public static final String DELIMITER_NAME = ", ";

    public void printDeckInitialized(List<String> names) {
        String joinedNames = String.join(DELIMITER_NAME, names);
        System.out.printf(MESSAGE_DECK_INITIALIZED, joinedNames);
    }
}
