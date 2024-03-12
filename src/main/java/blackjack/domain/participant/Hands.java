package blackjack.domain.participant;

import blackjack.domain.deck.Card;
import java.util.ArrayList;
import java.util.List;

public class Hands {

    private final List<Card> hands;

    public Hands() {
        this.hands = new ArrayList<>();
    }

    public void addCard(Card card) {
        hands.add(card);
    }

    public HandsScore getHandsScore() {
        return HandsScore.of(hands);
    }


    public boolean isBurst() {
        return getHandsScore().isBurst();
    }

    public List<Card> getHands() {
        return hands;
    }
}
