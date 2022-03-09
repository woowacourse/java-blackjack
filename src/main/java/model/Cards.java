package model;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static final int BLACK_JACK_SCORE = 21;
    private static final int SCORE_GAP_PER_ACE = 10;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public boolean canReceiveCard() {
        return getSum() < BLACK_JACK_SCORE;
    }

    int getSum() {
        if (countAce() == 0) {
            return getMinimumSum();
        }
        return convertableAceCount() * SCORE_GAP_PER_ACE + getMinimumSum();
    }

    private int convertableAceCount() {
        return Math.min((BLACK_JACK_SCORE - getMinimumSum()) / SCORE_GAP_PER_ACE, countAce());
    }

    private int getMinimumSum() {
        return cards.stream()
                .mapToInt(card -> card.getCardScore())
                .sum();
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(card -> card.isAceCard())
                .count();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean canReceiveCardForDealer() {
        return getSum() <= 16;
    }

    public Card getFirstCard(int index) {
        return cards.get(index);
    }

    public Result getResult(Cards other) {
        if (this.getStatus() == Status.STAND && other.getStatus() == Status.STAND) {
            return Result.of(this.getSum(), other.getSum());
        }
        return Result.of(this.getStatus(), other.getStatus());
    }

    private Status getStatus() {
        return Status.of(cards.size(), getSum());
    }

    public List<Card> getValue() {
        return cards;
    }
}
