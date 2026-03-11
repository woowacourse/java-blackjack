package controller;

public class BlackjackController {

    private GameContext gameContext;
    private EnterPlayerPhase enterPlayerPhase;
    private BettingPhase bettingPhase;
    private InitialDealPhase initialDealPhase;

    public BlackjackController() {
        gameContext = new GameContext();
        enterPlayerPhase = new EnterPlayerPhase();
        bettingPhase = new BettingPhase();
        initialDealPhase = new InitialDealPhase();
    }

    public void run() {
        enterPlayerPhase.execute(gameContext);
        bettingPhase.execute(gameContext);
        initialDealPhase.execute(gameContext);
    }
}
