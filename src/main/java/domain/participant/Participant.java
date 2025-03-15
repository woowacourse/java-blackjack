package domain.participant;

import domain.Score;
import domain.card.Card;
import domain.card.Cards;

import java.util.List;

public abstract class Participant {
    private final Cards cards;

    protected Participant(Cards cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Score getScore() {
        return cards.getScore();
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public List<Card> getCards() {
        return cards.getValues();
    }

    public abstract List<Card> getShowCards();
}
