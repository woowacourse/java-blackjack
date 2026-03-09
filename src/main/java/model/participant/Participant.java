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

    public void validateHasCards() {
        if (hands.isEmpty()) {
            throw new IllegalStateException("가진 카드 패가 없어 오픈할 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }

    public abstract Cards open();

    public abstract boolean beats(Participant participant);
}
