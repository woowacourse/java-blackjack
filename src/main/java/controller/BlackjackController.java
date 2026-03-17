package controller;

import controller.phase.FinishBlackJackPhase;
import controller.phase.GamePhase;
import controller.phase.PlayBlackJackPhase;
import controller.phase.PrepareBlackJackPhase;
import java.util.List;

public class BlackjackController {

    private final List<GamePhase> gamePhases;

    public BlackjackController() {
        gamePhases = List.of(
                new PrepareBlackJackPhase(),
                new PlayBlackJackPhase(),
                new FinishBlackJackPhase()
        );
    }

    public void run() {
        GameContext gameContext = new GameContext();
        for (GamePhase gamePhase : gamePhases) {
            gamePhase.execute(gameContext);
        }
    }
}
