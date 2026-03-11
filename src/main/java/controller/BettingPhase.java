package controller;

import model.Player;
import view.InputView;

public class BettingPhase implements GamePhase {

    public BettingPhase() {
    }

    @Override
    public void execute(GameContext gameContext) {
        for (Player player : gameContext.getPlayers()) {
            int bettingMoney = InputView.readPlayerBettingMoney(player.getName());
            player.setMoney(bettingMoney);
        }
    }
}
