package blackjack.model.player;

import blackjack.model.card.BlackJackCards;
import java.util.Collections;
import java.util.Objects;

public abstract class BlackJackPlayer {

    protected static final int BLACKJACK_POINT = 21;

    protected final String name;
    protected final BlackJackCards blackJackCards;

    protected BlackJackPlayer(final String name) {
        this.name = name;
        this.blackJackCards = BlackJackCards.empty();
    }

    public final void receiveCards(final BlackJackCards blackJackCards) {
        this.blackJackCards.merge(blackJackCards);
    }

    public final BlackJackCards openAllCards() {
        return blackJackCards;
    }

    public final int calculateOptimalPoint() {
        return blackJackCards.calculatePossiblePoints().stream()
                .filter(point -> point <= BLACKJACK_POINT)
                .max(Integer::compareTo)
                .orElse(getMinimumPoint());
    }

    public final boolean hasCardSize(final int size) {
        return blackJackCards.hasSize(size);
    }

    protected final int getMinimumPoint() {
        return Collections.min(blackJackCards.calculatePossiblePoints());
    }

    protected final boolean isBust() {
        return calculateOptimalPoint() > BLACKJACK_POINT;
    }

    public final String getName() {
        return name;
    }

    public final BlackJackCards getCards() {
        return blackJackCards;
    }

    public abstract BlackJackCards openInitialCards();

    protected abstract boolean canDrawMoreCard();

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BlackJackPlayer blackJackPlayer)) {
            return false;
        }
        return Objects.equals(name, blackJackPlayer.name) && Objects.equals(
                blackJackCards, blackJackPlayer.blackJackCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, blackJackCards);
    }

}
