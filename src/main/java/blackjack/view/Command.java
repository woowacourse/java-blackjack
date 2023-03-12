package blackjack.view;

import java.util.Arrays;

enum Command {

    YES("y", true),
    NO("n", false);

    private final String command;
    private final boolean condition;

    Command(final String command, final boolean condition) {
        this.command = command;
        this.condition = condition;
    }

    static Command from(final String query) {
        return Arrays.stream(Command.values())
                .filter(a -> a.equalsByQuery(query))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y 혹은 n 을 입력해야합니다."));
    }

    private boolean equalsByQuery(final String query) {
        return command.equalsIgnoreCase(query);
    }

    boolean getCondition() {
        return condition;
    }

    public String value() {
        return this.command;
    }
}
