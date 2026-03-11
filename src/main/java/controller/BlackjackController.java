package controller;

public class BlackjackController {

    private GameContext gameContext;
    private EnterPlayerPhase enterPlayerPhase;
    private BettingPhase bettingPhase;
    private InitialDealPhase initialDealPhase;
    private PlayerTurnPhase playerTurnPhase;

    public BlackjackController() {
        gameContext = new GameContext();
        enterPlayerPhase = new EnterPlayerPhase();
        bettingPhase = new BettingPhase();
        initialDealPhase = new InitialDealPhase();
        playerTurnPhase = new PlayerTurnPhase();
    }

    public void run() {
        enterPlayerPhase.execute(gameContext);
        bettingPhase.execute(gameContext);
        initialDealPhase.execute(gameContext);
        playerTurnPhase.execute(gameContext);
    }
}
