package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Participant {

    protected static final int BLACK_JACK_SCORE = 21;
    protected static final int ACE_ALTER_VALUE = 10;

    protected final Cards cards;

    public Participant() {
        this.cards = new Cards();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Score calculateScore() {
        Score score = cards.sum();
        int aceCount = cards.getAceCount();
        return score.calculateBestScoreAce(aceCount);
    }

    public abstract boolean canReceive();

    public Cards getCards() {
        return cards;
    }
}
