package domain;

public class DrawCommand {

    private final String command;

    public DrawCommand(final String command) {
        validate(command);
        this.command = command;
    }

    private void validate(final String command) {
        if (!(command.equals(Message.DRAW_COMMAND.getMessage()) || command.equals(Message.STOP_COMMAND.getMessage()))) {
            throw new IllegalArgumentException(Message.COMMAND_NOT_PERMITTED.getMessage());
        }
    }

    public boolean isDraw() {
        return command.equals(Message.DRAW_COMMAND.getMessage());
    }

    public boolean isStop() {
        return command.equals(Message.STOP_COMMAND.getMessage());
    }
}
