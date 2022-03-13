package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.card.Card;
import model.card.Cards;
import model.cardGettable.CardsGettable;

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

    public abstract boolean canReceiveCard();

    public String getPlayerName() {
        return name;
    }

    public boolean isSameName(String otherName) {
        return name.equals(otherName);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cardsGettableStrategy.getCards(cards));
    }

    public int getSum() {
        return cards.getSum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participator that = (Participator) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
