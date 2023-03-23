package domain.command;

import exception.IllegalDrawCommandException;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DrawCommand {
    DRAW("y"),
    STOP("n");

    private static final Map<String, DrawCommand> inputDrawCommandMap = Stream.of(values())
            .collect(Collectors.toMap(DrawCommand::getCommand, Function.identity()));

    private final String command;

    DrawCommand(String command) {
        this.command = command;
    }

    public static DrawCommand of(String inputCommand) {
        if(inputDrawCommandMap.containsKey(inputCommand)) {
            return inputDrawCommandMap.get(inputCommand);
        }
        throw new IllegalDrawCommandException();
    }

    private String getCommand() {
        return command;
    }
}
