package blackjack.domain.participant.human;

import blackjack.domain.cards.Cards;
import blackjack.domain.participant.human.name.Name;
import blackjack.domain.result.Betting;
import blackjack.domain.result.Result;

public final class Player extends Human {
    private Betting betting;

    public Player(final Name name) {
        super(new Cards(), name);
    }

    public Player initBetting(int betting) {
        this.betting = new Betting(betting);
        return this;
    }

    public boolean isCardsThatSize(final int size) {
        return cards.size() == size;
    }

    public boolean hasMorePoint(final Dealer dealer) {
        return getPoint() > dealer.getPoint();
    }

    public boolean isDraw(final Dealer dealer) {
        return dealer.getPoint() == getPoint();
    }

    public int getMultipliedMoney(double scale) {
        return betting.getMultipliedMoney(scale);
    }
}
