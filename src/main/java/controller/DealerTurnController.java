package controller;

import model.card.Deck;
import model.turn.DealerTurn;

public class DealerTurnController {
    private final DealerTurn dealerTurn;

    public DealerTurnController(DealerTurn dealerTurn) {
        this.dealerTurn = dealerTurn;
    }

    public void dealInitialCards(Deck deck) {
        dealerTurn.dealInitialCards(deck);
    }

    public void runDealerTurn(Deck deck){
        dealerTurn.runDealerTurn(deck);
    }

}
