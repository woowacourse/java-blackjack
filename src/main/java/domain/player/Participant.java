package domain.player;

import domain.card.Card;
import domain.card.Deck;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    private final Deck hands;

    public Participant() {
        this.hands = Deck.makeEmptyDeck();
    }

    public boolean isBust() {
//        return calculateScore() > PERFECT_SCORE;
        return false;
    }

    public boolean isNotBust() {
        return !isBust();
    }

//    public abstract boolean canHit();

    public void hit(final Card card) {
        hands.add(card);
    }

    public String getName() {
        throw new IllegalCallerException("참여자의 이름이 정해지지 않았습니다");
    }

    public List<Card> getHands() {
        return Collections.unmodifiableList(hands.getValue());
    }
}
