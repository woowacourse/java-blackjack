package model.turn;

import model.participant.Dealer;

public class DealerTurn extends Turn {

    public DealerTurn(Dealer dealer) {
        super(dealer);
    }

    public Dealer getDealer() {
        return (Dealer) participant;
    }
}
