package blackjack.view;

import java.util.Arrays;

public enum Command {

    YES("y", true),
    NO("n", false);

    private final String input;
    private final boolean value;

    Command(final String input, final boolean value) {
        this.input = input;
        this.value = value;
    }

    public static Command from(final String input) {
        return Arrays.stream(Command.values())
                .filter(command -> input.equals(command.input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y 혹은 n만 입력해주세요"));
    }

    public boolean isValue() {
        return value;
    }
}
