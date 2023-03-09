package domain.player;

import domain.deck.Card;
import domain.deck.Rank;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    public static final int ABLE_ACE_ADDITIONAL_SCORE_UPPERBOUND = 11;
    public static final int ACE_ADDITIONAL_SCORE = 10;
    private static final int BLACKJACK_CARDS_SIZE = 2;
    private static final int BLACKJACK_NUMBER = 21;

    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public List<Card> cards() {
        return List.copyOf(cards);
    }

    public int score() {
        int score = 0;

        for (Card card : cards) {
            score += card.getScore();
        }
        if (isAbleAddAceAdditionalScore(score)) {
            score += ACE_ADDITIONAL_SCORE;
        }

        return score;
    }

    private boolean isAbleAddAceAdditionalScore(final int score) {
        return hasAce() && score <= ABLE_ACE_ADDITIONAL_SCORE_UPPERBOUND;
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(card -> card.getRank().equals(Rank.ACE));
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_CARDS_SIZE && score() == BLACKJACK_NUMBER;
    }
}
