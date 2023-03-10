package view;

import java.util.Arrays;

public enum Command {

    YES("y"),
    NO("n");

    private final String input;

    Command(String input) {
        this.input = input;
    }

    public static Command from(String input) {
        return Arrays.stream(Command.values())
                .filter(command -> command.hasInput(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("예는 y, 아니오는 n 입니다"));
    }

    private boolean hasInput(String input) {
        return this.input.equals(input);
    }

    public boolean isYes() {
        return this == YES;
    }
}
