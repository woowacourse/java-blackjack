package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int MAX_LIMIT_SCORE = 21;

    private final List<Card> myCards;

    public Hand(List<Card> myCards) {
        this.myCards = new ArrayList<>(myCards);
    }

    public void add(List<Card> cards) {
        myCards.addAll(cards);
    }

    public void add(Card card) {
        myCards.add(card);
    }

    public int calculateScore() {
        boolean hasAce = false;
        int sum = 0;

        for (Card card : myCards) {
            if (card.rank() == Rank.ACE && !hasAce) {
                hasAce = true;
                sum += card.rank().get(1);
                continue;
            }
            sum += card.rank().get(0);
        }

        if (sum > MAX_LIMIT_SCORE && hasAce) {
            sum = sum - Rank.ACE.get(1) + Rank.ACE.get(0);
        }
        return sum;
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
