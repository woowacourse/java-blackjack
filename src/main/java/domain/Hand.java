package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private final List<TrumpCard> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public Hand(List<TrumpCard> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(final TrumpCard card) {
        cards.add(card);
    }

    public int calculateScore() {
        int score = 0;
        for (final TrumpCard card : cards) {
            score = card.sumScore(score);
        }

        return Rank.ifBustAceIsMIN(score, calculateAceCount());
    }

    private int calculateAceCount() {
        return (int)cards.stream()
            .filter(card -> card.isMatchRank(Rank.ACE))
            .count();
    }

    public List<TrumpCard> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
