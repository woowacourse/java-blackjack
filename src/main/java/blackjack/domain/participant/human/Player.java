package blackjack.domain.participant.human;

import blackjack.domain.cards.Cards;

public final class Player extends Human {

    private Player(final String name) {
        super(new Cards(), name);
    }

    public static Player from(final String name) {
        return new Player(name);
    }

    public boolean isCardsThatSize(final int size) {
        return cards.size() == size;
    }

    public boolean isWinner(final Dealer dealer) {
        return dealer.getPoint().compareTo(getPoint()) < 0;
    }

    public boolean isDraw(final Dealer dealer) {
        return dealer.getPoint().equals(getPoint());
    }
}
