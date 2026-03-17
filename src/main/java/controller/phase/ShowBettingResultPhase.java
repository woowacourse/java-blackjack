package controller.phase;

import controller.GameContext;
import view.OutputView;

public class ShowBettingResultPhase implements GamePhase {

    public ShowBettingResultPhase() {
    }

    @Override
    public void execute(GameContext gameContext) {
        gameContext.calculateBettingMoney();
        printBettingResult(gameContext);
    }

    private void printBettingResult(GameContext gameContext) {
        OutputView.printBettingResultHeader();
        OutputView.printBettingResult(gameContext.participantsBettingResults());
    }
}
