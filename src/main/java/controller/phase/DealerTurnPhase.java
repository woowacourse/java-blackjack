package controller.phase;

import controller.GameContext;
import model.Dealer;
import view.OutputView;

public class DealerTurnPhase implements GamePhase {

    public DealerTurnPhase() {
    }

    @Override
    public void execute(GameContext gameContext) {
        Dealer dealer = gameContext.dealer();
        while (dealer.canHit()) {
            OutputView.printToOpenDealerNewCard(dealer.name());
            gameContext.dispenseOneCard(dealer);
        }
    }
}
