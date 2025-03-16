package blackjack.constant;

import java.util.Arrays;

public enum UserAction {

    HIT("y"),
    STAND("n"),
    ;

    private final String command;

    UserAction(String command) {
        this.command = command;
    }

    public static UserAction from(String input) {
        return Arrays.stream(values())
                .filter(action -> action.command.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 올바른 기능을 입력해 주세요."));
    }

}
