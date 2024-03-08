package ui;

import domain.blackjackgame.BlackjackAction;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String DEFAULT_DELIMITER = ",";
    private static final String DEFAULT_DELIMITER_NAME = "쉼표";
    private static final List<String> INVALID_PLAYER_NAMES = List.of("딜러", "dealer");

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<String> readPlayerNames() {
        System.out.printf("게임에 참여할 사람의 이름을 입력하세요.(%s 기준으로 분리)%n", DEFAULT_DELIMITER_NAME);
        String playerNames = scanner.nextLine();
        System.out.println();
        validateInput(playerNames);
        return toList(playerNames);
    }

    private List<String> toList(String input) {
        List<String> playerNames = Arrays.asList(input.split(DEFAULT_DELIMITER, -1));
        validateInvalidPlayerName(playerNames);
        return playerNames;
    }

    private void validateInvalidPlayerName(List<String> playerNames) {
        if (hasInvalidPlayerName(playerNames)) {
            throw new IllegalArgumentException("딜러와 연관된 이름이 포함되어 있습니다.");
        }
    }

    private boolean hasInvalidPlayerName(List<String> playerNames) {
        return playerNames.stream()
                .anyMatch(INVALID_PLAYER_NAMES::contains);
    }

    public String readBlackjackAction(String name) {
        System.out.printf("%s는 한 장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)%n",
                name, BlackjackAction.HIT.getExpression(), BlackjackAction.STAY.getExpression());
        String action = scanner.nextLine();
        validateInput(action);
        return action.trim();
    }

    private void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력값이 비어있습니다.");
        }
    }
}
