package controller.command;

import model.betting.Betting;

public class SurrenderCommand implements PlayerCommand{
    private final Betting betting;

    public SurrenderCommand(Betting betting) {
        this.betting = betting;
    }

    @Override
    public void execute() {
        betting.surrender();
    }

}
