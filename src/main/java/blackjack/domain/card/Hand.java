package blackjack.domain.card;

import blackjack.domain.rule.HandCalculator;
import blackjack.domain.rule.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private final List<Card> hand = new ArrayList<>();

    public void add(Card card) {
        hand.add(card);
    }

    public Score calculate() {
        return HandCalculator.calculate(hand);
    }

    public boolean isBusted() {
        Score score = calculate();
        return score.isBusted();
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
    }

    public List<Card> getFirstHand() {
        return Collections.unmodifiableList(hand.subList(0, 1));
    }
}
