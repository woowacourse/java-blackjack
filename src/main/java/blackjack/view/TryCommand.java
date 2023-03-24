package blackjack.view;

import java.util.HashMap;
import java.util.Map;

public enum TryCommand {
    HIT("y", true),
    STAND("n", false);

    private static final Map<String, Boolean> TRY_COMMANDS = new HashMap<>();

    private final String command;
    private final boolean hit;

    TryCommand(String command, boolean hit) {
        this.command = command;
        this.hit = hit;
    }

    static {
        for (TryCommand tryCommand : TryCommand.values()) {
            TRY_COMMANDS.put(tryCommand.command, tryCommand.hit);
        }
    }

    public static boolean findCommand(String inputCommand) {
        if (TRY_COMMANDS.containsKey(inputCommand)) {
            return TRY_COMMANDS.get(inputCommand);
        }
        throw new IllegalArgumentException("잘못된 입력입니다.");
    }
}
