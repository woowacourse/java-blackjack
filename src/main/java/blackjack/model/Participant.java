package blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Participant {
    protected final List<Card> cards;

    public Participant() {
        this.cards = new ArrayList<>();
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public Score getScore() {
        return ScoreCalculator.calculate(cards);
    }

    public boolean isBurst() {
        return getScore().isBurst();
    }

    public Card getFirstCardName() {
        return cards.getFirst();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public abstract boolean canReceive();
}
