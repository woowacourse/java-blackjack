package blackjack.domain.player;

import java.util.Arrays;

public enum Command {
    HIT("y"),
    STAY("n")
    ;

    private final String symbol;

    Command(String symbol) {
        this.symbol = symbol;
    }

    public static Command of(String input) {
        return Arrays.stream(Command.values())
                .filter(command -> command.symbol.equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 명령입니다"));
    }
}
