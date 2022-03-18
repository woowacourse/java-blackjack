package domain.participant;

import domain.HitThreshold;
import domain.card.Card;
import domain.card.CardState;
import domain.card.Cards;

import java.util.List;

public abstract class Participant {
    protected final Cards cards;
    private final String name;

    protected Participant(final HitThreshold hitThreshold, final String name) {
        this.cards = new Cards(hitThreshold);
        this.name = name;
    }

    public boolean drawCard(final Card card) {
        return cards.add(card);
    }

    public boolean canDrawCard() {
        return cards.canAddCard();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public String getName() {
        return name;
    }

    public int getTotalScore() {
        return cards.calculateSum();
    }

    public CardState getCardState() {
        return cards.getCardState();
    }
}
