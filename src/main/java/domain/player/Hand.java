package domain.player;

import domain.deck.Card;
import domain.deck.Rank;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    public static final int UPPER_BOUND = 11;
    public static final int ACE_RANK_DIFFERENCE = 10;

    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(card -> card.getRank().equals(Rank.ACE.getRank()));
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public int score() {
        int sum = 0;

        for (Card card : cards) {
            sum += card.getScore();
        }
        if (hasAce() && sum <= UPPER_BOUND) {
            sum += ACE_RANK_DIFFERENCE;
        }

        return sum;
    }
}
