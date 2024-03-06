package view;

import java.util.Arrays;

public enum BlackJackGameCommand {
    YES("y"),
    NO("n");

    private final String command;

    BlackJackGameCommand(String command) {
        this.command = command;
    }

    public static boolean contains(String command) {
        return Arrays.stream(values())
                .anyMatch(value -> command.equals(value.command));
    }

    public String getCommand() {
        return command;
    }
}
