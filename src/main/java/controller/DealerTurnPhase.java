package controller;

import model.CardDispenser;
import model.Dealer;
import view.OutputView;

public class DealerTurnPhase implements GamePhase {

    public DealerTurnPhase() {
    }

    @Override
    public void execute(GameContext gameContext) {
        Dealer dealer = gameContext.dealer();
        CardDispenser cardDispenser = gameContext.cardDispenser();
        while (dealer.canHit()) {
            OutputView.printToOpenDealerNewCard(dealer.name());
            cardDispenser.dispenseOneCard(dealer);
        }
    }
}
