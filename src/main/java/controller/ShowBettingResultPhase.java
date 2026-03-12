package controller;

public class ShowBettingResultPhase implements GamePhase {

    public ShowBettingResultPhase() {
    }

    @Override
    public void execute(GameContext gameContext) {
        gameContext.bettingResult().calculateBettingMoney(gameContext.dealer(), gameContext.players());
        gameContext.bettingResult().printBettingResult();
    }
}
