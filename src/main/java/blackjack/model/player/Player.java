package blackjack.model.player;

import blackjack.model.card.Cards;
import java.util.Collections;
import java.util.Objects;

public abstract class Player {

    protected static final int BLACKJACK_POINT = 21;

    protected final String name;
    protected final Cards cards;

    protected Player(final String name) {
        this.name = name;
        this.cards = Cards.empty();
    }

    public int getMinimumPoint() {
        return Collections.min(cards.calculatePossiblePoints());
    }

    public void receiveCards(final Cards cards) {
        this.cards.merge(cards);
    }

    public Cards openAllCards() {
        return cards;
    }

    public int calculateOptimalPoint() {
        return cards.calculatePossiblePoints().stream()
                .filter(point -> point < BLACKJACK_POINT)
                .max(Integer::compareTo)
                .orElse(getMinimumPoint());
    }

    protected boolean isBust() {
        return calculateOptimalPoint() > BLACKJACK_POINT;
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }

    public abstract Cards openInitialCards();

    public abstract boolean canDrawMoreCard();

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Player player)) {
            return false;
        }
        return Objects.equals(name, player.name) && Objects.equals(cards, player.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cards);
    }

}
