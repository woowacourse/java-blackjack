package domain.participant;

import domain.card.Card;
import domain.card.CardDeck;
import java.util.List;

public abstract class AbstractGambler implements Gambler {

    protected final String name;
    protected final CardDeck cardDeck;

    protected AbstractGambler(String name) {
        this.name = name;
        this.cardDeck = new CardDeck();
    }

    public abstract boolean canTakeMoreCard();

    public void takeCards(Card... cards) {
        cardDeck.takeCards(cards);
    }

    public int calculateScore() {
        return cardDeck.calculateScore();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cardDeck.getCards();
    }
}
