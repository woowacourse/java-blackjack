package controller;

public class BlackjackController {

    private GameContext gameContext;
    private EnterPlayerPhase enterPlayerPhase;
    private BettingPhase bettingPhase;
    private InitialDealPhase initialDealPhase;
    private PlayerTurnPhase playerTurnPhase;
    private DealerTurnPhase dealerTurnPhase;
    private CalculateScorePhase calculateScorePhase;
    private ShowBettingResultPhase showBettingResultPhase;

    public BlackjackController() {
        gameContext = new GameContext();
        enterPlayerPhase = new EnterPlayerPhase();
        bettingPhase = new BettingPhase();
        initialDealPhase = new InitialDealPhase();
        playerTurnPhase = new PlayerTurnPhase();
        dealerTurnPhase = new DealerTurnPhase();
        calculateScorePhase = new CalculateScorePhase();
        showBettingResultPhase = new ShowBettingResultPhase();
    }

    public void run() {
        enterPlayerPhase.execute(gameContext);
        bettingPhase.execute(gameContext);
        initialDealPhase.execute(gameContext);
        playerTurnPhase.execute(gameContext);
        dealerTurnPhase.execute(gameContext);
        calculateScorePhase.execute(gameContext);
        showBettingResultPhase.execute(gameContext);
    }
}
