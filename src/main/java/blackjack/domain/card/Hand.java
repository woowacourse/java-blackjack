package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int MAX_LIMIT_SCORE = 21;

    private final List<Card> myCards;

    public Hand(List<Card> myCards) {
        this.myCards = new ArrayList<>(myCards);
    }

    public Hand copy() {
        return new Hand(myCards);
    }

    public void add(List<Card> cards) {
        myCards.addAll(cards);
    }

    public void add(Card card) {
        myCards.add(card);
    }

    public int calculateScore() {
        int sumExceptAce = getSumExceptAce();
        int countAce = getAceCount();

        if (countAce > 0) {
            return sumExceptAce + getAceSum(sumExceptAce, countAce);
        }
        return sumExceptAce;
    }

    private int getSumExceptAce() {
        return myCards.stream()
                .filter(card -> card.rank() != Rank.ACE)
                .mapToInt(card -> card.rank().get(0))
                .sum();
    }

    private int getAceCount() {
        return (int) myCards.stream()
                .filter(card -> card.rank() == Rank.ACE)
                .count();
    }

    private int getAceSum(int sumExceptAce, int countAce) {
        int sumAce = Rank.ACE.get(1) + (countAce - 1) * Rank.ACE.get(0);
        if (sumExceptAce + sumAce <= MAX_LIMIT_SCORE) {
            return sumAce;
        }
        return sumAce - Rank.ACE.get(1) + Rank.ACE.get(0);
    }

    public boolean isOverLimitScore() {
        return calculateScore() > MAX_LIMIT_SCORE;
    }

    public boolean isLimitScore() {
        return calculateScore() == MAX_LIMIT_SCORE;
    }

    public int getNumberOfCards() {
        return myCards.size();
    }

    public List<Card> getMyCards() {
        return myCards;
    }

    public Card getMyCardAt(int index) {
        return myCards.get(index);
    }
}
