package model.participant;

import static model.GameRule.BLACKJACK_SCORE;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.card.Card;
import model.card.Cards;
import model.card.Rank;

public abstract class Participant {
    private final String name;
    protected final List<Card> hands;

    protected Participant(String name) {
        this.name = name;
        this.hands = new ArrayList<>();
    }

    public List<Card> receive(Card card) {
        hands.add(card);
        return List.copyOf(hands);
    }

    public int calculateScore() {
        int total = calculate();
        int aceCardCount = aceCount();
        while (aceCardCount-- > 0 && total > BLACKJACK_SCORE) {
            total -= 10;
        }

        return total;
    }

    public boolean canHit() {
        return calculateScore() < BLACKJACK_SCORE;
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK_SCORE;
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

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Participant that)) {
            return false;
        }
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public abstract Cards open();

    public abstract boolean beats(Participant participant);
}
