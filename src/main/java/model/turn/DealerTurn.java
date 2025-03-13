package model.turn;

import model.card.Deck;
import model.participant.Dealer;
import view.OutputView;

public class DealerTurn extends Turn{
    private final Dealer dealer;

    public DealerTurn(Dealer dealer) {
        super(dealer);
        this.dealer = dealer;
    }

    public void runDealerTurn(Deck deck) {
        while (dealer.checkScoreUnderSixteen()) {
            OutputView.printDealerDealResult();
            dealer.receiveCard(deck.pick());
        }
    } // DealerTurn으로 이동
}
