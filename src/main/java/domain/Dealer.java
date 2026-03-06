package domain;

import domain.card.Card;

import java.util.List;

public class Dealer extends Participant {
    private final Deck deck = new Deck();

    public Dealer() {
        super();
    }

    public List<Card> handOutFirstHandCards() {
        return deck.firstHandCards();
    }

    public Card handOutMoreCard() {
        return deck.drawCard();

    }

    public boolean isReceiveCard() {
        return calculateScore() <= 16;
    }
}
