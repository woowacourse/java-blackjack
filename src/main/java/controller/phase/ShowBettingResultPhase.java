package controller.phase;

import controller.GameContext;
import model.BettingResult;
import view.OutputView;

public class ShowBettingResultPhase implements GamePhase {

    public ShowBettingResultPhase() {
    }

    @Override
    public void execute(GameContext gameContext) {
        gameContext.bettingResult().calculateBettingMoney(gameContext.dealer(), gameContext.players());
        printBettingResult(gameContext.bettingResult());
    }

    private void printBettingResult(BettingResult bettingResult) {
        OutputView.printBettingResultHeader();
        OutputView.printBettingResult((bettingResult).participantsBettingResults());
    }
}
