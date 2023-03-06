package controller;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DrawCardCommand {

    CARD_DRAW_AGAIN("y"),
    CARD_DRAW_STOP("n");

    private static final Map<String, DrawCardCommand> CACHE = Stream.of(DrawCardCommand.values())
            .collect(Collectors.toUnmodifiableMap(cardCommand -> cardCommand.command, Function.identity()));

    private final String command;

    DrawCardCommand(final String command) {
        this.command = command;
    }

    public static DrawCardCommand findCardCommand(final String command) {
        return Optional.ofNullable(CACHE.get(command.toLowerCase()))
                .orElseThrow(() -> new IllegalArgumentException(getExceptionMessage()));
    }

    private static String getExceptionMessage() {
        return String.format("%s 혹은 %s를 입력해 주세요.", CARD_DRAW_AGAIN.command, CARD_DRAW_STOP.command);
    }

    public boolean isDrawAgain() {
        return this == CARD_DRAW_AGAIN;
    }

    public String getCommand() {
        return command;
    }
}
