package model.turn;

import model.card.Deck;
import model.participant.Dealer;
import view.OutputView;

public class DealerTurn extends Turn {

    public DealerTurn(Dealer dealer) {
        super(dealer);
    }

    public void runDealerTurn(Deck deck) {
        Dealer dealer = (Dealer) participant;
        while (dealer.checkScoreUnderSixteen()) {
            OutputView.printDealerDealResult();
            dealer.receiveCard(deck.pick());
        }
    }
}
