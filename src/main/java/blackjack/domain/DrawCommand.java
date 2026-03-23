package blackjack.domain;

public enum DrawCommand {
    HIT("y"),
    STAND("n");

    private final String command;

    DrawCommand(String command) {
        this.command = command;
    }

    public static DrawCommand from(String userCommand) {
        for (DrawCommand drawCommand : values()) {
            if (drawCommand.command.equals(userCommand)) {
                return drawCommand;
            }
        }
        throw new IllegalArgumentException("유효한 커맨드(예는 y, 아니오는 n)를 입력해 주세요.");
    }

    public boolean isDraw() {
        return this == HIT;
    }
}
