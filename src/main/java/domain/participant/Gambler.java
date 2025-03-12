package domain.participant;

import domain.card.Card;
import domain.card.CardHand;
import java.util.List;

public abstract class Gambler {

    protected final String name;
    protected final CardHand cardHand;

    protected Gambler(String name, CardHand cardHand) {
        this.name = name;
        this.cardHand = cardHand;
    }

    public abstract boolean canTakeMoreCard();

    public void takeCards(Card... cards) {
        cardHand.takeCards(cards);
    }

    public int calculateScore() {
        return cardHand.calculateScore();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cardHand.getCards();
    }
}

