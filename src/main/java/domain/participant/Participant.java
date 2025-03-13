package domain.participant;

import domain.Score;
import domain.card.Card;
import domain.card.Cards;

import java.util.List;

public abstract class Participant {
    private static final int BLACKJACK_COUNT = 2;
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
        Score score = cards.getScore();
        return score.isHit() && cards.getCount() == BLACKJACK_COUNT;
    }

    public Cards getCards() {
        return cards;
    }

    public abstract List<Card> getShowCards();
}
