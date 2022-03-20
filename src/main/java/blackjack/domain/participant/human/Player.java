package blackjack.domain.participant.human;

import blackjack.domain.cards.Cards;
import blackjack.domain.cards.card.Card;
import blackjack.domain.participant.human.name.Name;
import blackjack.domain.result.BetAmount;
import blackjack.domain.state.State;
import blackjack.domain.state.running.Hit;
import java.util.List;

public final class Player extends Human {

    private final BetAmount betAmount;
    private final State state;

    public Player(final Name name, final int betting, final List<Card> cards) {
        super(new Cards(cards), name);
        this.betAmount = new BetAmount(betting);
        this.state = new Hit(this.cards);
    }

    public boolean hasCardSizeOf(final int size) {
        return cards.size() == size;
    }

    public boolean hasMorePoint(final Dealer dealer) {
        return getPoint() > dealer.getPoint();
    }

    public boolean isDrawState(final Dealer dealer) {
        return dealer.getPoint() == getPoint();
    }

    public int getMultipliedMoney(double scale) {
        return betAmount.getMultipliedMoney(scale);
    }

    public State getState() {
        return state;
    }
}
