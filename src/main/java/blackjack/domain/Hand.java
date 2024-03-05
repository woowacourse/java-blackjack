package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void add(final Card card) {
        this.cards.add(card);
    }

    public int calculateScore() {
        int score = 0;

        for (Card card : cards) {
            score += card.getScore().get(0);
        }

        return score;
    }

    public List<Card> getCards() {
        return cards;
    }
}
