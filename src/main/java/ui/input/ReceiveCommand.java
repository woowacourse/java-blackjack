package ui.input;

import java.util.Arrays;

public enum ReceiveCommand {
    HIT("y"),
    STAY("n");

    private final String command;

    ReceiveCommand(final String command) {
        this.command = command;
    }

    public static ReceiveCommand of(final String command) {
        return Arrays.stream(ReceiveCommand.values())
                .filter(receiveCommand -> receiveCommand.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y 또는 n을 입력해주세요."));
    }

    public static boolean isHit(final ReceiveCommand receiveCommand) {
        return HIT.equals(receiveCommand);
    }
}
