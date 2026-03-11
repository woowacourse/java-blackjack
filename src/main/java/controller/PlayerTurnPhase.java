package controller;

import model.CardDispenser;
import model.Player;
import view.InputView;
import view.OutputView;

public class PlayerTurnPhase implements GamePhase {

    private CardDispenser cardDispenser;

    public PlayerTurnPhase() {
    }

    @Override
    public void execute(GameContext gameContext) {
        this.cardDispenser = gameContext.getCardDispenser();
        for (Player player : gameContext.getPlayers()) {
            chooseHitOrStand(player);
        }
    }

    private void chooseHitOrStand(Player player) {
        boolean drawCard = false;
        while (canHitMore(player)) {
            distributeMoreOneCard(player);
            drawCard = true;
        }
        if (!drawCard) {
            OutputView.printCardByPlayer(player);
        }
    }

    private boolean canHitMore(Player player) {
        return player.canHit() && readContinuation(player).isContinue();
    }

    private Continuation readContinuation(Player player) {
        String inputCommand = InputView.readMoreCard(player.getName());
        return Continuation.from(inputCommand);
    }

    private void distributeMoreOneCard(Player player) {
        cardDispenser.dispenseOneCard(player);
        OutputView.printCardByPlayer(player);
    }
}
