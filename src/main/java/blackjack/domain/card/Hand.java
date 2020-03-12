package blackjack.domain.card;

import blackjack.domain.rule.CardCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {

    private final List<Card> hand = new ArrayList<>();

    public void add(Card card) {
        hand.add(card);
    }

    public int calculateSum() {
        return CardCalculator.calculate(hand);
    }

    public String getCardStatus() {
        return hand.stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "));
    }

    public List<Card> getHand() {
        return hand;
    }
}
