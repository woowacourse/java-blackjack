package second.domain.player;

import second.domain.card.HandCards;

public class Player extends Gamer {
    public Player(String name) {
        this(name, new HandCards());
    }

    public Player(String name, HandCards handCards) {
        super(name, handCards);
    }

    @Override
    public boolean canDrawMore() {
        return isBust();
    }
}
