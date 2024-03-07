package blackjack.domain.controller;

import blackjack.view.PlayerCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CommandController {
    private final Map<PlayerCommand, Supplier<Boolean>> runnable;

    public CommandController() {
        this.runnable = new HashMap<>();
    }

    public void put(PlayerCommand playerCommand, Supplier<Boolean> supplier) {
        runnable.put(playerCommand, supplier);
    }

    public boolean run(String command) {
        PlayerCommand playerCommand = PlayerCommand.from(command);
        return runnable.get(playerCommand).get();
    }
}
