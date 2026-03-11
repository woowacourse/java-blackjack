package controller;

public class BlackjackController {

    private GameContext gameContext;
    private EnterPlayerPhase enterPlayerPhase;
    private BettingPhase bettingPhase;

    public BlackjackController() {
        gameContext = new GameContext();
        enterPlayerPhase = new EnterPlayerPhase();
        bettingPhase = new BettingPhase();
    }

    public void run() {
        enterPlayerPhase.execute(gameContext);
        bettingPhase.execute(gameContext);
    }
}
