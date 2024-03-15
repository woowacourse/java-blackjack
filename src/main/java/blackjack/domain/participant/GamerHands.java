package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.hands.Hands;
import blackjack.domain.hands.HandsScore;

import java.util.List;

public class GamerHands {
    private final Name name;
    private final Hands hands;

    public GamerHands (Name name) {
        this.name = name;
        this.hands = new Hands();
    }

    public void addCard(Card card) {
        hands.addCard(card);
    }

    public boolean isBust() {
        return hands.isBust();
    }

    public boolean isBlackJack() {
        return hands.isBlackJack();
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
}
