package domain;

import java.util.LinkedList;
import java.util.List;

public class Hand {

    public static final int UPPER_BOUND = 11;
    public static final int ACE_RANK_DIFFERENCE = 10;

    private final LinkedList<Card> cards;

    public Hand() {
        cards = new LinkedList<>();
    }

    public void addCard(final Card card) {
        if (isAce(card)) {
            cards.add(card);
            return;
        }
        cards.addFirst(card);
    }

    private static boolean isAce(final Card card) {
        return card.getRank().equals(Rank.ACE);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public int score() {
        int sum = 0;

        for (Card card : cards) {
            sum += card.getRank().getScore();
        }
        if (isAce(cards.getLast()) && sum <= UPPER_BOUND) {
            sum += ACE_RANK_DIFFERENCE;
        }

        return sum;
    }
}
