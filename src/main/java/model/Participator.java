package model;

import java.util.ArrayList;
import java.util.List;
import model.card.Card;
import model.card.Cards;
import model.cardGettable.CardsGettable;
import model.cardGettable.EveryCardsGettable;

public abstract class Participator {
    protected final Cards cards;
    protected CardsGettable cardsGettableStrategy;
    private final String name;

    public Participator(String name) {
        this.cards = new Cards(new ArrayList<>());
        this.name = name;
    }

    public void receiveCard(Card card) {
        cards.addCard(card);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cardsGettableStrategy.getCards(cards));
    }

    public abstract boolean canReceiveCard();

    public String getPlayerName() {
        return name;
    }

    public boolean isSameName(String otherName) {
        return name.equals(otherName);
    }

    public int getSum() {
        return cards.getSum();
    }
}
