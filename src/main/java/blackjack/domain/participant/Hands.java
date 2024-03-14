package blackjack.domain.participant;

import blackjack.domain.deck.Card;
import java.util.ArrayList;
import java.util.List;

public class Hands {

    private static final int INITIAL_DRAW_CARD_NUMBER = 2;
    private static final int BLACK_JACK = 21;

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

    public int compareHandsScore(Hands otherHands) {
        if (getHandsScore() == otherHands.getHandsScore()) {
            return 0;
        }
        return getHandsScore() - otherHands.getHandsScore();
    }

    public boolean isHigherScore(Hands otherHands) {
        return getHandsScore() > otherHands.getHandsScore();
    }

    public boolean isSameScore(Hands otherHands) {
        return getHandsScore() == otherHands.getHandsScore();
    }

    public List<Card> getHands() {
        return hands;
    }

    public int size() {
        return hands.size();
    }

    public boolean isBlackJack() {
        return (size() == INITIAL_DRAW_CARD_NUMBER) && (getHandsScore() == BLACK_JACK);
    }
}
