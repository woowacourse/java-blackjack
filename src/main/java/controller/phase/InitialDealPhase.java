package controller.phase;

import controller.GameContext;
import java.util.List;
import model.Dealer;
import model.Player;
import view.OutputView;

public class InitialDealPhase implements GamePhase {

    @Override
    public void execute(GameContext gameContext) {
        OutputView.printCardOpen(gameContext.players());
        distributeCardToDealer(gameContext.dealer(), gameContext);
        distributeCardToPlayer(gameContext.players(), gameContext);
    }

    private void distributeCardToDealer(Dealer dealer, GameContext gameContext) {
        gameContext.dispenseStartingCards(dealer);
    }

    private void distributeCardToPlayer(List<Player> players, GameContext gameContext) {
        for (Player player : players) {
            gameContext.dispenseStartingCards(player);
        }
    }
}
