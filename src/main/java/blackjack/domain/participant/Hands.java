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

    public int getHandsScore() {
        return HandsScore.of(hands).getScore();
    }

    public boolean isBust() {
        return HandsScore.of(hands).isBust();
    }

    public List<Card> getHands() {
        return hands;
    }

    public int size() {
        return hands.size();
    }
}
