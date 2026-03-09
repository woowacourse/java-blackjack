package domain.participant;

import domain.card.Deck;
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

    public Card getFirstCard() {
        return getHandCards().getFirst();
    }

    public boolean isReceiveCard() {
        return calculateScore() <= 16;
    }
}
