package domain;

import domain.card.Card;

import java.util.List;

public class Dealer extends Participant {
    private final Deck deck = new Deck();

    public Dealer() {
        super("딜러");
    }

    public List<Card> dealInitialCards() {
        return deck.firstHandCards();
    }

    public Card dealHitCard() {
        return deck.drawCard();
    }

    public List<Card> getFirstCard() {
        return getHandCards().subList(0, 1);
    }

    public boolean isReceiveCard() {
        return calculateScore() <= 16;
    }
}
