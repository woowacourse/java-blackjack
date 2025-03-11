package domain.participant;

import domain.card.Card;
import domain.card.CardHand;
import java.util.List;

public abstract class AbstractGambler implements Gambler {

    protected final String name;
    protected final CardHand cardHand;

    protected AbstractGambler(String name) {
        this.name = name;
        this.cardHand = new CardHand();
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
