package domain;

public class DrawCommand {

    private static final String DRAW_COMMAND = "y";
    private static final String STOP_COMMAND = "n";

    private final String command;

    public DrawCommand(final String command) {
        validate(command);
        this.command = command;
    }

    private void validate(final String command) {
        if (!(command.equals(DRAW_COMMAND) || command.equals(STOP_COMMAND))) {
            throw new IllegalArgumentException("y혹은 n을 입력해주세요.");
        }
    }

    public boolean isDraw() {
        return command.equals(DRAW_COMMAND);
    }

    public boolean isStop() {
        return command.equals(STOP_COMMAND);
    }
}
