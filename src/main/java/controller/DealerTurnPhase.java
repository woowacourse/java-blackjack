package controller;

import model.CardDispenser;
import model.Dealer;
import view.OutputView;

public class DealerTurnPhase implements GamePhase {

    public DealerTurnPhase() {
    }

    @Override
    public void execute(GameContext gameContext) {
        Dealer dealer = gameContext.getDealer();
        CardDispenser cardDispenser = gameContext.getCardDispenser();
        while (dealer.canHit()) {
            OutputView.printToOpenDealerNewCard(dealer.getName());
            cardDispenser.dispenseOneCard(dealer);
        }
    }
}
