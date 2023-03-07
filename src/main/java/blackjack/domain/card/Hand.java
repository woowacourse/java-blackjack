package blackjack.domain.card;

import blackjack.domain.participant.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private final List<Card> hand = new ArrayList<>();

    public void add(Card card) {
        hand.add(card);
    }

    public Score sum() {
        return hand.stream()
                .map(card -> new Score(card.getDenomination().getValue()))
                .reduce(new Score(0), Score::increase);
    }

    public int getAceCount() {
        return (int) hand.stream()
                .filter(card -> card.getDenomination().equals(Denomination.ACE))
                .count();
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
    }

    public int getCount() {
        return hand.size();
    }

}
