package controller.mode;

import controller.input.BettingPlayerReader;
import controller.result.ProfitReporter;

public class BettingGameMode extends GameMode {

    public BettingGameMode() {
        super(new BettingPlayerReader(), new ProfitReporter());
    }
}
