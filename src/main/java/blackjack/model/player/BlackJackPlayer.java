package blackjack.model.player;

import blackjack.model.card.BlackJackCards;
import blackjack.model.player.money.Money;
import java.util.Collections;

public abstract class BlackJackPlayer {

    protected static final int BLACKJACK_POINT = 21;

    protected final String name;
    protected final BlackJackCards blackJackCards;
    protected final Money money;

    protected BlackJackPlayer(final String name, final Money money) {
        this.name = name;
        this.blackJackCards = BlackJackCards.empty();
        this.money = money;
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

    public abstract BlackJackCards openInitialCards();

    protected abstract boolean canDrawMoreCard();

    public final String getName() {
        return name;
    }

    public final BlackJackCards getCards() {
        return blackJackCards;
    }

    public final int getMoney() {
        return money.getValue();
    }
}
