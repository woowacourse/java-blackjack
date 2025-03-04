package domain;

import java.util.ArrayList;

import java.util.List;

public record Dealer(List<Card> hand) {

    public Dealer(){
        this(new ArrayList<>());
    }

    public void pickUpCard(Deck deck) {
        var card = deck.pickCard();
        hand.add(card);
    }
}
