package blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Participant {
    protected final List<Card> cards;
    protected int score;

    public Participant() {
        this.cards = new ArrayList<>();
        this.score = 0;
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public void addScore(int score) {
        this.score += score;
    }

    public int getSize() {
        return cards.size();
    }

    public int getScore() {
        return score;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public abstract boolean canReceive(int score);
}
