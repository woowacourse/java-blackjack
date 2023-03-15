package domain.player;

import domain.deck.Card;
import domain.deck.Rank;

import java.util.LinkedList;
import java.util.List;

public final class Hand {

    private static final int UPPER_BOUND = 11;
    private static final int ACE_RANK_DIFFERENCE = 10;

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

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
