package second.domain.player;

import second.domain.card.HandCards;

public class Player extends Gamer {
    public Player(final String name) {
        this(name, new HandCards());
    }

    public Player(final String name, HandCards handCards) {
        super(name, handCards);
    }

    @Override
    public boolean canDrawMore() {
        return !isBust();
    }
}
