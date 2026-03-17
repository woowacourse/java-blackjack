package controller.phase;

import controller.GameContext;
import view.OutputView;

public class CalculateScorePhase implements GamePhase {

    public CalculateScorePhase() {
    }

    @Override
    public void execute(GameContext gameContext) {
        OutputView.printBlank();
        OutputView.printCardByPlayerWithScore(gameContext.dealer(), gameContext.dealer().calculateTotalScore());
        gameContext.players().forEach(
                player -> OutputView.printCardByPlayerWithScore(player, player.calculateTotalScore())
        );
    }
}
