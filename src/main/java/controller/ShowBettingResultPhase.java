package controller;

public class ShowBettingResultPhase implements GamePhase {

    public ShowBettingResultPhase() {
    }

    @Override
    public void execute(GameContext gameContext) {
        gameContext.getBettingResult().calculateBettingMoney(gameContext.getDealer(), gameContext.getPlayers());
        gameContext.getBettingResult().printBettingResult();
    }
}
