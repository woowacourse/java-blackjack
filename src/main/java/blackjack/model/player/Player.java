package blackjack.model.player;

import blackjack.model.card.Cards;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Player {

    protected final String name;
    protected final Cards cards;
    protected final Role role;

    protected Player(final String name, final Role role) {
        this.name = name;
        this.cards = Cards.empty();
        this.role = role;
    }

    public List<Integer> calculatePossiblePoints() {
        return cards.calculatePossiblePoints();
    }

    public int getMinimumPoint() {
        return Collections.min(cards.calculatePossiblePoints());
    }

    public void receiveCards(final Cards cards) {
        this.cards.merge(cards);
    }

    public boolean hasRole(final Role role) {
        return this.role == role;
    }

    public Cards getCards() {
        return cards;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Player player)) {
            return false;
        }
        return Objects.equals(name, player.name) && Objects.equals(getCards(), player.getCards())
                && role == player.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, getCards(), role);
    }

}
