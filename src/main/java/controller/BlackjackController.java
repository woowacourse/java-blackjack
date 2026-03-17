package controller;

import controller.phase.BettingPhase;
import controller.phase.CalculateScorePhase;
import controller.phase.DealerTurnPhase;
import controller.phase.EnterPlayerPhase;
import controller.phase.GamePhase;
import controller.phase.InitialDealPhase;
import controller.phase.PlayerTurnPhase;
import controller.phase.ShowBettingResultPhase;
import java.util.List;

public class BlackjackController {

    private final GameContext gameContext;
    private final List<GamePhase> gamePhaseList;

    public BlackjackController() {
        gameContext = new GameContext();
        gamePhaseList = List.of(
                new EnterPlayerPhase(),
                new BettingPhase(),
                new InitialDealPhase(),
                new PlayerTurnPhase(),
                new DealerTurnPhase(),
                new CalculateScorePhase(),
                new ShowBettingResultPhase()
        );
    }

    public void run() {
        for (GamePhase gamePhase : gamePhaseList) {
            gamePhase.execute(gameContext);
        }
    }
}
