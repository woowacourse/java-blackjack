package domain;

import java.util.Arrays;

public enum Command {
    YES("Y"),
    NO("N");

    private final String displayCommand;

    Command(final String displayCommand) {
        this.displayCommand = displayCommand;
    }

    public static Command find(final String input) {
        return Arrays.stream(Command.values())
                .filter(command -> command.getDisplayCommand().equals(input.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령어입니다."));
    }

    public String getDisplayCommand() {
        return displayCommand;
    }
}
