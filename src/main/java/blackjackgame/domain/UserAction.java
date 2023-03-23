package blackjackgame.domain;

import java.util.Arrays;

public enum UserAction {
    HIT("y"),
    STAY("n");

    private final String command;

    UserAction(String command) {
        this.command = command;
    }

    public static UserAction of(String inputCommand) {
        return Arrays.stream(values())
                .filter(userAction -> userAction.command.equals(inputCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("카드 드로우 커맨드는 y,n 둘 중 하나입니다."));
    }
}
