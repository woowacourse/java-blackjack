package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Deck;
import java.util.List;

public abstract class Participant {

    protected final Cards cards;
    protected final Name name;

    public Participant(Name name) {
        this.cards = Cards.getEmptyCardsPack();
        this.name = name;
    }

    public abstract boolean canHit();

    public abstract void hit(Deck deck);

    public void receiveInitialTwoCards(Deck deck) {
        cards.addCards(deck.handOutInitialTwoCards());
    }

    public int calculateScore() {
        return cards.calculateScore();
    }

    protected boolean isBust() {
        return cards.isBust();
    }

    protected boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }
}
