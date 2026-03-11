package controller;

import model.CardDispenser;
import model.Dealer;
import view.OutputView;

public class DealerTurnPhase implements GamePhase {
    private Dealer dealer;
    private CardDispenser cardDispenser;

    public DealerTurnPhase() {
    }

    @Override
    public void execute(GameContext gameContext) {
        this.dealer = gameContext.getDealer();
        this.cardDispenser = gameContext.getCardDispenser();
        while (dealer.canHit()) {
            OutputView.printToOpenDealerNewCard(dealer.getName());
            cardDispenser.dispenseOneCard(dealer);
        }
    }
}
