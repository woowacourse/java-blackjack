package controller;

import model.BettingMoney;
import model.Player;
import view.InputView;

public class BettingPhase implements GamePhase {

    public BettingPhase() {
    }

    @Override
    public void execute(GameContext gameContext) {
        for (Player player : gameContext.players()) {
            BettingMoney bettingMoney = InputView.readPlayerBettingMoney(player.name());
            player.setMoney(bettingMoney);
        }
    }
}
