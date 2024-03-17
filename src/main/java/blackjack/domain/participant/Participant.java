package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.hands.Hands;
import blackjack.domain.hands.HandsScore;

import java.util.List;

public abstract class Participant {
    private final Hands hands;

    protected Participant(){
        this.hands= new Hands();
    }

    abstract boolean canAddCard();

    public void addCard(Card card) {
        hands.addCard(card);
    }

    public boolean isFirstTurnBackJack() {
        return this.isBlackJack() && hands.getHands().size() == 2;
    }

    public boolean isBlackJack() {
        return hands.isBlackJack();
    }

    public boolean isBust() {
        return hands.isBust();
    }

    public List<Card> getHandsCards() {
        return hands.getHands();
    }

    public HandsScore getHandsScore() {
        return hands.getHandsScore();
    }
}
