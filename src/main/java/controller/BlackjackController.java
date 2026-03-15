package controller;

import java.util.List;

public class BlackjackController {

    private GameContext gameContext;
    private List<GamePhase> gamePhaseList;

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
