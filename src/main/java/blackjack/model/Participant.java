package blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Participant {
    protected final List<Card> cards;
    protected final Score score;

    public Participant() {
        this.cards = new ArrayList<>();
        this.score = new Score();
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public void updateScore() {
        score.calculateAll(cards);
    }

    public boolean isBurst() {
        return score.isBurst();
    }

    public int getScore() {
        return score.getScore();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public abstract boolean canReceive(int score);
}
