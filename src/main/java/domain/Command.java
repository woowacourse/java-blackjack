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
                .filter(command -> command.getDisplayCommand().equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령어입니다."));
    }

    public static boolean isYes(final String input) {
        return Command.YES.getDisplayCommand().equals(input.toUpperCase());
    }

    public static boolean isNo(final String input) {
        return Command.NO.getDisplayCommand().equals(input.toUpperCase());
    }


    public String getDisplayCommand() {
        return displayCommand;
    }
}

