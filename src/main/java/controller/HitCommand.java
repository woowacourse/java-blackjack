package controller;

import java.util.Arrays;

public enum HitCommand {
    YES("y"),
    NO("n");

    private final String command;

    HitCommand(String command) {
        this.command = command;
    }

    public static void validateCommand(String input) {
        Arrays.stream(HitCommand.values())
                .filter(hitCommand -> hitCommand.getCommand().equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y또는 n만 입력 가능합니다."));
    }

    public static boolean isYes(String input) {
        return input.equals(YES.getCommand());
    }

    public String getCommand() {
        return command;
    }
}
