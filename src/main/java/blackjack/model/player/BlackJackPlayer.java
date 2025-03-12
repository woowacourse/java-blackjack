package blackjack.model.player;

import blackjack.model.card.Cards;
import java.util.Collections;
import java.util.Objects;

public abstract class BlackJackPlayer {

    protected static final int BLACKJACK_POINT = 21;

    protected final String name;
    protected final Cards cards;

    protected BlackJackPlayer(final String name) {
        this.name = name;
        this.cards = Cards.empty();
    }

    public final void receiveCards(final Cards cards) {
        this.cards.merge(cards);
    }

    public final Cards openAllCards() {
        return cards;
    }

    public final int calculateOptimalPoint() {
        return cards.calculatePossiblePoints().stream()
                .filter(point -> point < BLACKJACK_POINT)
                .max(Integer::compareTo)
                .orElse(getMinimumPoint());
    }

    protected final int getMinimumPoint() {
        return Collections.min(cards.calculatePossiblePoints());
    }

    protected final boolean isBust() {
        return calculateOptimalPoint() > BLACKJACK_POINT;
    }

    public final String getName() {
        return name;
    }

    public final Cards getCards() {
        return cards;
    }

    public abstract Cards openInitialCards();

    protected abstract boolean canDrawMoreCard();

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BlackJackPlayer blackJackPlayer)) {
            return false;
        }
        return Objects.equals(name, blackJackPlayer.name) && Objects.equals(cards, blackJackPlayer.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cards);
    }

}
