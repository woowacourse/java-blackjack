package domain.command;

import domain.message.ExceptionMessage;

public class DrawCommand {

    private final String command;

    public DrawCommand(final String command) {
        validate(command);
        this.command = command;
    }

    private void validate(final String command) {
        if (!(command.equals(ExceptionMessage.DRAW_COMMAND.getMessage()) || command.equals(ExceptionMessage.STOP_COMMAND.getMessage()))) {
            throw new IllegalArgumentException(ExceptionMessage.COMMAND_NOT_PERMITTED.getMessage());
        }
    }

    public boolean isDraw() {
        return command.equals(ExceptionMessage.DRAW_COMMAND.getMessage());
    }

    public boolean isStop() {
        return command.equals(ExceptionMessage.STOP_COMMAND.getMessage());
    }
}
