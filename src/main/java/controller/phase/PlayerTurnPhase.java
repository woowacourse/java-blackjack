package controller.phase;

import controller.Continuation;
import controller.GameContext;
import model.Player;
import view.InputView;
import view.OutputView;

public class PlayerTurnPhase implements GamePhase {

    @Override
    public void execute(GameContext gameContext) {
        for (Player player : gameContext.players()) {
            chooseHitOrStand(player, gameContext);
        }
    }

    private void chooseHitOrStand(Player player, GameContext gameContext) {
        boolean drawCard = false;
        while (canHitMore(player)) {
            distributeMoreOneCard(player, gameContext);
            printCardByPlayer(player);
            drawCard = true;
        }
        if (!drawCard) {
            printCardByPlayer(player);
        }
    }

    private boolean canHitMore(Player player) {
        return player.canHit() && readContinuation(player).isContinue();
    }

    private Continuation readContinuation(Player player) {
        String inputCommand = InputView.readMoreCard(player.name());
        return Continuation.from(inputCommand);
    }

    private void distributeMoreOneCard(Player player, GameContext gameContext) {
        gameContext.dispenseOneCard(player);
    }

    private void printCardByPlayer(Player player) {
        OutputView.printCardByPlayer(player);
    }
}
