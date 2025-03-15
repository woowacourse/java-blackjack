package domain;

import java.util.Arrays;
import java.util.ResourceBundle;

public enum Command {
    YES,
    NO;

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("command");

    public static Command find(final String input) {
        return Arrays.stream(Command.values())
                .filter(command -> resourceBundle.getString(command.name()).equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령어입니다."));
    }

    public static boolean isYes(final String input) {
        return resourceBundle.getString(Command.YES.name())
                .equals(input.toUpperCase());
    }

    public static boolean isNo(final String input) {
        return resourceBundle.getString(Command.NO.name())
                .equals(input.toUpperCase());
    }
}

