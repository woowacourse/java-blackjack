package domain;

import java.util.ArrayList;
import java.util.List;

public record CardHand(
    List<Card> hand
) {

    public CardHand() {
        this(new ArrayList<>());
    }

    public void add(final Card card) {
        hand.add(card);
    }

    public int calculateAllScore() {
        int score = 0;
        int aceCount = calculateAceCount();
        for (final Card card : hand) {
            score = card.sumNumber(score);
            if (score > 21 && aceCount > 0) {
                aceCount--;
                score -= 10;
            }
        }
        return score;
    }

    private int calculateAceCount() {
        return (int)hand.stream()
            .filter(card -> card.isMatchNumber(CardNumber.ACE))
            .count();
    }
}
