package domain.participant;

import java.util.List;

import domain.card.Card;

public abstract class Participant {

    protected Name name;
    protected HandCards handCards;

    public Participant(Name name, HandCards handCards) {
        this.name = name;
        this.handCards = handCards;
    }

    public void takeCard(Card card) {
        handCards.addCard(card);
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getHandCards() {
        return handCards.getCards();
    }

    public int getSize() {
        return handCards.getSize();
    }
}
