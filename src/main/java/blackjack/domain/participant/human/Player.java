package blackjack.domain.participant.human;

import blackjack.domain.cards.Cards;
import blackjack.domain.cards.card.Card;
import blackjack.domain.participant.human.name.Name;
import blackjack.domain.result.BetAmount;
import java.util.List;

public final class Player extends Human {
    private BetAmount betAmount;

    public Player(final Name name) {
        super(new Cards(), name);
    }

    public Player initBetting(int betting) {
        this.betAmount = new BetAmount(betting);
        return this;
    }

    public void addCards(final List<Card> cards) {
        cards.forEach(this.cards::add);
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
}
