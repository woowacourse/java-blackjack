package blackjackgame.domain;

import java.util.Arrays;

public enum DrawCommand {
    DRAW("y"),
    STOP("n");

    private final String command;

    DrawCommand(String command) {
        this.command = command;
    }

    public static DrawCommand of(String inputCommand) {
        return Arrays.stream(values())
                .filter(drawCommand -> drawCommand.command.equals(inputCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("카드 드로우 커맨드는 y,n 둘 중 하나입니다."));
    }
}
