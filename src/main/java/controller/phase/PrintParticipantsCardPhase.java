package controller.phase;

import controller.GameContext;
import view.OutputView;

public class PrintParticipantsCardPhase implements GamePhase {

    @Override
    public void execute(GameContext gameContext) {
        OutputView.printCardByDealer(gameContext.dealer());
        OutputView.printCardByPlayers(gameContext.players());
    }
}
