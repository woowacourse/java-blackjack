package controller.phase;

import controller.GameContext;
import java.util.List;
import model.Dealer;
import model.Participant;
import model.Player;
import view.OutputView;

public class InitialDealPhase implements GamePhase {

    @Override
    public void execute(GameContext gameContext) {
        OutputView.printCardOpen(gameContext.players());
        distributeCardToDealer(gameContext.dealer(), gameContext);
        distributeCardToPlayer(gameContext.players(), gameContext);
        checkBlackJack(gameContext.dealer());
        for (Player player : gameContext.players()) {
            checkBlackJack(player);
        }
    }

    private void distributeCardToDealer(Dealer dealer, GameContext gameContext) {
        gameContext.dispenseStartingCards(dealer);
    }

    private void distributeCardToPlayer(List<Player> players, GameContext gameContext) {
        for (Player player : players) {
            gameContext.dispenseStartingCards(player);
        }
    }

    private void checkBlackJack(Participant participant) {
        participant.checkBlackJack();
    }
}
