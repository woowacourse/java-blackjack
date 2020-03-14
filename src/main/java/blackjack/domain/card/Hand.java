package blackjack.domain.card;

import blackjack.domain.rule.CardCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private final List<Card> hand = new ArrayList<>();

    public void add(Card card) {
        hand.add(card);
    }

    public int calculateSum() {
        return CardCalculator.calculate(hand);
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
    }

    public List<Card> getFirstHand() {
        return Collections.unmodifiableList(hand.subList(0, 1));
    }
}
