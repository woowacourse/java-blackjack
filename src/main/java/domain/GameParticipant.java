package domain;

import java.util.List;

public class GameParticipant {
    protected final String name;
    protected final CardHand cardHand;

    public GameParticipant(String name, CardHand cardHand) {
        this.name = name;
        this.cardHand = cardHand;
    }

    public void hit(Card newCard) {
        cardHand.add(newCard);
    }

    public int calculateScore() {
        return cardHand.calculateScore();
    }

    public boolean isBust() {
        return cardHand.isBust();
    }

    public boolean isBlackJack() {
        return cardHand.isBlackJack();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cardHand.getCards();
    }
}
