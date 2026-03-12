package controller;

import java.util.List;
import model.CardDispenser;
import model.Dealer;
import model.Participant;
import model.Player;
import view.OutputView;

public class InitialDealPhase implements GamePhase {

    CardDispenser cardDispenser;

    public InitialDealPhase() {
    }

    @Override
    public void execute(GameContext gameContext) {
        this.cardDispenser = gameContext.getCardDispenser();
        OutputView.printCardOpen(gameContext.getPlayers());

        distributeCardToDealer(gameContext.getDealer());
        distributeCardToPlayer(gameContext.getPlayers());
        checkBlackJack(gameContext.getDealer());
        for (Player player : gameContext.getPlayers()) {
            checkBlackJack(player);
        }
    }

    private void distributeCardToDealer(Dealer dealer) {
        cardDispenser.dispenseStartingCards(dealer);
        OutputView.printCardByDealer(dealer);
    }

    private void distributeCardToPlayer(List<Player> players) {
        for (Player player : players) {
            cardDispenser.dispenseStartingCards(player);
        }
        OutputView.printCardByPlayers(players);
    }

    private void checkBlackJack(Participant participant) {
        participant.checkBlackJack();
    }
}
