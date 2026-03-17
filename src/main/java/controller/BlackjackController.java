package controller;

import controller.phase.BettingPhase;
import controller.phase.CalculateScorePhase;
import controller.phase.DealerTurnPhase;
import controller.phase.EnterPlayerPhase;
import controller.phase.GamePhase;
import controller.phase.InitialDealPhase;
import controller.phase.PlayerTurnPhase;
import controller.phase.PrintParticipantsCardPhase;
import controller.phase.ShowBettingResultPhase;
import java.util.List;

public class BlackjackController {

    private final List<GamePhase> gamePhases;

    public BlackjackController() {
        gamePhases = List.of(
                new EnterPlayerPhase(),
                new BettingPhase(),
                new InitialDealPhase(),
                new PrintParticipantsCardPhase(),
                new PlayerTurnPhase(),
                new DealerTurnPhase(),
                new CalculateScorePhase(),
                new ShowBettingResultPhase()
        );
    }

    public void run() {
        GameContext gameContext = new GameContext();
        for (GamePhase gamePhase : gamePhases) {
            gamePhase.execute(gameContext);
        }
    }
}
