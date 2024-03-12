package view;

import java.util.Arrays;

public enum Command {
    YES("y"), NO("n");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command get(String input) {
        return Arrays.stream(values())
                .filter(command -> command.value.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("명령어는 y,n만 가능합니다."));
    }
}
