package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Player {
    private final List<Card> hand = new ArrayList<>();

    public void receiveInitCard(List<Card> cards) {
        hand.addAll(cards);
    }

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
    }

}
