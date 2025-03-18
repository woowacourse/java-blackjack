package model.turn;

import model.card.Deck;
import model.participant.Dealer;
import view.OutputView;

public class DealerTurn implements Turn {
    private final Dealer dealer;

    public DealerTurn(Dealer dealer) {
        this.dealer = dealer;
    }

    @Override
    public void dealInitialCards(Deck deck) {
        dealCards(dealer, deck);
    }

    public void runDealerTurn(Deck deck) {
        while (dealer.checkScoreUnderSixteen()) {
            OutputView.printDealerDealResult();
            dealer.receiveCard(deck.pick());
        }
    }
}
