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
        this.cardDispenser = gameContext.cardDispenser();
        OutputView.printCardOpen(gameContext.players());

        distributeCardToDealer(gameContext.dealer());
        distributeCardToPlayer(gameContext.players());
        checkBlackJack(gameContext.dealer());
        for (Player player : gameContext.players()) {
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
