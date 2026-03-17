package controller.phase;

import controller.GameContext;
import model.BettingMoney;
import model.Player;
import view.InputView;

public class BettingPhase implements GamePhase {

    @Override
    public void execute(GameContext gameContext) {
        for (Player player : gameContext.players()) {
            BettingMoney bettingMoney = InputView.readPlayerBettingMoney(player.name());
            player.setMoney(bettingMoney);
        }
    }
}
