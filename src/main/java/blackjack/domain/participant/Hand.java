package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private final List<Card> hand;

    public Hand() {
        this.hand = new ArrayList<>();
    }

    public void receiveCard(final Card card) {
        hand.add(card);
    }

    public int calculateScore() {
        return hand.stream()
                .mapToInt(Card::getCardValue)
                .sum();
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
    }
}
