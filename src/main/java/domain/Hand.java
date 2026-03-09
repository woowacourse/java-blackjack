package domain;

import constant.GameConstant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Hand implements Iterable<Card> {
    private static final int ACE_BONUS_SCORE = 10;
    private static final int ACE_NO_BONUS = 0;

    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public int getSize() {
        return cards.size();
    }

    public Card getFirst() {
        return cards.getFirst();
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(c -> c.cardRank().equals(CardRank.ACE));
    }

    public int calculateScore() {
        int total = 0;
        for (Card card : cards) {
            total += card.cardRank().getValue();
        }
        return total;
    }

    private int calculateAceScore() {
        if (!hasAce() || calculateScore() > 11) {
            return ACE_NO_BONUS;
        }
        return ACE_BONUS_SCORE;
    }

    public int calculateFinalScore() {
        return calculateScore() + calculateAceScore();
    }

    public boolean isBust() {
        return calculateFinalScore() > GameConstant.BUST_THRESHOLD;
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
