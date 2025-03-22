package domain.participant;

import domain.card.Card;
import domain.card.CardHand;
import java.util.List;

public abstract class GameParticipant {
    protected final String name;
    protected final CardHand cardHand;

    protected GameParticipant(String name, CardHand cardHand) {
        this.name = name;
        this.cardHand = cardHand;
    }

    public abstract boolean canHit();

    public void hit(Card newCard) {
        cardHand.add(newCard);
    }

    public boolean isBlackJack() {
        return cardHand.isBlackJack();
    }

    public boolean isBust() {
        return cardHand.isBust();
    }

    public int calculateScore() {
        return cardHand.calculateScore();
    }

    public List<Card> getCards() {
        return cardHand.getCards();
    }

    public String getName() {
        return name;
    }
}
