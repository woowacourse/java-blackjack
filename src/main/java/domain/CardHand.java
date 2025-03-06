package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardHand {

    private final List<Card> cards;

    public CardHand() {
        this.cards = new ArrayList<>();
    }

    public CardHand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public int calculateAllScore() {
        int score = 0;
        int aceCount = calculateAceCount();
        for (final Card card : cards) {
            score = card.sumNumber(score);
            if (score > 21 && aceCount > 0) {
                aceCount--;
                score -= 10;
            }
        }
        return score;
    }

    private int calculateAceCount() {
        return (int)cards.stream()
            .filter(card -> card.isMatchNumber(CardNumber.ACE))
            .count();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
