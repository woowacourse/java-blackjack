package blackjack.model.player;

import java.util.Collections;
import java.util.List;
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

    public List<Integer> calculatePossiblePoints() {
        return cards.calculatePossiblePoints();
    }

    public int getMinimumPoint() {
        return Collections.min(cards.calculatePossiblePoints());
    }

    public void receiveCards(final Cards cards) {
        this.cards.merge(cards);
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
