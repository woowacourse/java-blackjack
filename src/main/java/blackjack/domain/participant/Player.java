package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.hands.Hands;
import blackjack.domain.hands.HandsScore;
import blackjack.domain.hands.Name;

import java.util.List;

public class Player {
    private final Name name;
    private final Hands hands;

    public Player(String name) {
        this.name = new Name(name);
        this.hands = new Hands();
    }

    public boolean canAddCard() {
        return (!hands.isBust());
    }

    public boolean isFirstTurnBackJack() {
        return hands.isBlackJack() && hands.getHands().size() == 2;
    }

    public void addCard(Card card) {
        hands.addCard(card);
    }

    public boolean isBust() {
        return hands.isBust();
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
