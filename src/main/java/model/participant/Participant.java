package model.participant;

import java.util.ArrayList;
import java.util.List;
import model.card.Card;
import model.card.Cards;
import model.card.Rank;

public abstract class Participant {
    private static final int BUST_THRESHOLD = 21;

    private final String name;
    protected final List<Card> hands;

    protected Participant(String name) {
        this.name = name;
        this.hands = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Card> receive(Card card) {
        hands.add(card);
        return List.copyOf(hands);
    }

    public int calculateScore() {
        int total = calculate();
        int aceCardCount = aceCount();
        while (aceCardCount-- > 0 && total > 21) {
            total -= 10;
        }

        return total;
    }

    public boolean beats(Participant participant) {
        if (participant.isBust()) {
            return true;
        }

        return calculateScore() > participant.calculateScore();
    }

    public boolean canHit() {
        return calculateScore() < BUST_THRESHOLD;
    }

    public boolean isBust() {
        return calculateScore() > BUST_THRESHOLD;
    }

    private int calculate() {
        return hands.stream()
                .map(Card::getRank)
                .mapToInt(Rank::toValue)
                .sum();
    }

    private int aceCount() {
        int count = 0;
        for (Card card : hands) {
            if (card.getRank() == Rank.ACE) {
                count++;
            }
        }

        return count;
    }

    public abstract Cards open();
}
