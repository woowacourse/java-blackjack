package blackjack.constant;

import java.util.Arrays;
import java.util.Optional;

public enum Command {

    HIT("y"),
    STAY("n");

    private final String name;
    public static final String NOT_FOUND_COMMAND_EXCEPTION_MESSAGE = "[ERROR] 존재하지 않는 명령어입니다.";

    Command(String name) {
        this.name = name;
    }

    public static Optional<Command> of(String input) {
        return Arrays.stream(values())
                .filter(command -> command.name.equalsIgnoreCase(input))
                .findAny();
    }
}
