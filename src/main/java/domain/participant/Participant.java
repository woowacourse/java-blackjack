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

    public int getCardScore() {
        return cards.getScore().value();
    }

    public boolean isBust() {
        Score score = cards.getScore();
        return score.isBust();
    }

    public boolean isHit() {
        Score score = cards.getScore();
        return score.isHit();
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public List<Card> getCards() {
        return cards.getValues();
    }

    public abstract List<Card> getShowCards();
}
