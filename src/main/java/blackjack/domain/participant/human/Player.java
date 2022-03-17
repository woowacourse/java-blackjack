package blackjack.domain.participant.human;

import blackjack.domain.cards.Cards;
import blackjack.domain.participant.human.name.Name;
import blackjack.domain.result.Betting;
import blackjack.domain.result.Result;

public final class Player extends Human {
    private Betting betting;

    private Player(final Name name) {
        super(new Cards(), name);
    }

    public static Player fromName(final Name name) {
        return new Player(name);
    }

    public static Player fromText(final String name) {
        return new Player(Name.valueOf(name));
    }

    public boolean isCardsThatSize(final int size) {
        return cards.size() == size;
    }

    public boolean hasMorePoint(final Dealer dealer) {
        return dealer.getPoint().compareTo(getPoint()) < 0;
    }

    public boolean isDraw(final Dealer dealer) {
        return dealer.getPoint().equals(getPoint());
    }

    public Player initBetting(int betting) {
        this.betting = new Betting(betting);
        return this;
    }

    public Betting getBetting() {
        return betting;
    }

    public Result calculateResult(Dealer dealer) {
        if (dealer.isBust()) {
            return Result.fromBoolean(!isBust());
        }
        if (!isBust() && isDraw(dealer)) {
            return Result.DRAW;
        }
        return Result.fromBoolean(!isBust() && hasMorePoint(dealer));
    }
}
