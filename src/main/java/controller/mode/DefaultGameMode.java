package controller.mode;

import controller.input.NonBettingPlayerReader;
import controller.result.WinLossReporter;

public class DefaultGameMode extends GameMode {

    public DefaultGameMode() {
        super(new NonBettingPlayerReader(), new WinLossReporter());
    }
}
