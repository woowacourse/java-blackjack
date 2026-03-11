package controller;

import view.OutputView;

public class CalculateScorePhase implements GamePhase {

    CalculateScorePhase() {
    }

    @Override
    public void execute(GameContext gameContext) {
        OutputView.printBlank();
        OutputView.printCardByPlayerWithScore(gameContext.getDealer());
        gameContext.getPlayers().forEach(OutputView::printCardByPlayerWithScore);
    }
}
