package domain.command;

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
        throw new IllegalArgumentException("[ERROR] 카드 드로우 커맨드는 y,n 둘 중 하나입니다.");
    }

    private String getCommand() {
        return command;
    }
}
