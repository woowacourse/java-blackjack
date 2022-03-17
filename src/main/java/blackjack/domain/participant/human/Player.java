package blackjack.domain.participant.human;

import blackjack.domain.cards.Cards;

public final class Player extends Human {
    private static final int INIT_CARD_NUMBER = 2;

    private Player(String name) {
        super(new Cards(), name);
    }

    public static Player from(String name) {
        return new Player(name);
    }

    public boolean isTwoCard() {
        return cards.size() == INIT_CARD_NUMBER;
    }

    public boolean isWinner(Dealer dealer) {
        return dealer.getPoint() < getPoint();
    }

    public boolean isDraw(Dealer dealer) {
        return dealer.getPoint() == getPoint();
    }
}
