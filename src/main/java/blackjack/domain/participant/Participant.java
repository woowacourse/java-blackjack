package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.hands.Hands;
import blackjack.domain.hands.HandsScore;

import java.util.List;

// TODO 메서드 순서 - business method > getter > abstract

public abstract class Participant {

    protected final Name name;
    protected final Hands hands;

    protected Participant(Name name) {
        this.name = name;
        this.hands = new Hands();
    }

    abstract boolean canAddCard();

    public void addCard(Card card) {
        hands.addCard(card);
    }

    public List<Card> getHandsCards() {
        return hands.getHands();
    }

    public String getName() {
        return name.getName();
    }

    public HandsScore getHandsScore() {
        return hands.getHandsScore();
    }

    public boolean hasHigherScore(Participant participant) {
        return hands.getHandsScore()
                .isHigherThan(participant.getHandsScore());
    }

    public boolean hasSameScore(Participant participant) {
        return hands.getHandsScore()
                .isSame(participant.getHandsScore());
    }

    public boolean hasLowerScore(Participant participant) {
        return hands.getHandsScore()
                .isLowerThan(participant.getHandsScore());
    }

    public boolean isBust() {
        return hands.isBurst();
    }

    public boolean isNotBust() {
        return !isBust();
    }

    public boolean isBlackJack() {
        return hands.isBlackJack();
    }

    public boolean isNotBlackJack() {
        return !isBlackJack();
    }
}
