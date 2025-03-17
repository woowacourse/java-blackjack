package blackjack.model.player;

import java.util.Objects;

import blackjack.model.card.Cards;

public abstract class Player {

    protected final String name;
    protected final Cards cards;

    protected Player(final String name) {
        this.name = name;
        this.cards = Cards.empty();
    }

    public abstract boolean isDealer();

    public abstract Cards openCards();

    public abstract boolean canDrawMoreCard();

    public void receiveCards(final Cards cards) {
        this.cards.merge(cards);
    }

    public int calculatePoint() {
        return cards.calculateOptimalPoint();
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public Cards getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Player player)) {
            return false;
        }
        return Objects.equals(getName(), player.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

}
