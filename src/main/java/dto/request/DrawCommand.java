package dto.request;


import java.util.Arrays;

public enum DrawCommand {
    DRAW("y"),
    STOP("n");

    private final String command;

    DrawCommand(String command) {
        this.command = command;
    }

    public static DrawCommand from(String command) {
        return Arrays.stream(DrawCommand.values())
                .filter(drawCommand -> drawCommand.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y혹은 n을 입력해주세요."));
    }

    public boolean isDraw() {
        return this == DRAW;
    }

    public boolean isStop() {
        return this == STOP;
    }
}
