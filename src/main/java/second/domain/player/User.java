package second.domain.player;

import second.domain.card.HandCards;

public class User extends Player {
    public User(String name) {
        this(name, new HandCards());
    }

    public User(String name, HandCards handCards) {
        super(name, handCards);
    }

    @Override
    public boolean canDrawMore() {
        return isBust();
    }
}
