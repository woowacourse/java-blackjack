package blackjack.domain.hands;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

import static blackjack.domain.card.Value.ACE;

public class Hands {
    private static final HandsScore BLACK_JACK = new HandsScore(21);
    private final List<Card> hands;

    public Hands() {
        this.hands = new ArrayList<>();
    }

    public void addCard(Card card) {
        hands.add(card);
    }

    public HandsScore getHandsScore() {
        int score = calculateScoreSum();
        if (this.hasAce()) {
            return HandsScore.upgradeAceWhenNotBust(score);
        }
        return new HandsScore(score);
    }

    public boolean isBurst() {
        return getHandsScore().isHigherThan(BLACK_JACK);
    }

    public boolean isBlackJack() {
        return getHandsScore().isSame(BLACK_JACK);
    }

    private boolean hasAce() {
        return hands.stream()
                .anyMatch(card -> card.hasValue(ACE));
    }

    private int calculateScoreSum() {
        return hands.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public List<Card> getHands() {
        return hands;
    }
}
