package controller;

import model.BettingResult;

public class ShowBettingResultPhase implements GamePhase {

    public ShowBettingResultPhase() {
    }

    @Override
    public void execute(GameContext gameContext) {
        BettingResult.calculateBettingMoney(gameContext.getDealer(), gameContext.getPlayers());
        BettingResult.printBettingResult();
    }
}
