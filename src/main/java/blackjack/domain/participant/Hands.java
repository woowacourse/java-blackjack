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

    public int findHandsScore() {
        return HandsScore.of(hands).getScore();
    }

    public boolean isBust() {
        return HandsScore.of(hands).isBust();
    }

    public boolean isHigherScore(Hands otherHands) {
        return findHandsScore() > otherHands.findHandsScore();
    }

    public boolean isSameScore(Hands otherHands) {
        return findHandsScore() == otherHands.findHandsScore();
    }

    public boolean isBlackJack() {
        return (size() == INITIAL_DRAW_CARD_NUMBER) && (findHandsScore() == BLACK_JACK);
    }

    public String getFirstCardName() {
        return hands.get(0)
                .getCardName();
    }

    public List<Card> getHands() {
        return hands;
    }

    public int size() {
        return hands.size();
    }
}
