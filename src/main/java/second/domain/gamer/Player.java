package second.domain.gamer;

import second.domain.card.HandCards;

public class Player extends Gamer {
    // TODO : 빌더 패턴 적용해보기
    public Player(final String name) {
        this(name, new HandCards(), new Money());
    }

    public Player(final String name, Money money) {
        this(name, new HandCards(), money);
    }

    public Player(final String name, HandCards handCards) {
        this(name, handCards, new Money());
    }

    public Player(final String name, HandCards handCards, Money money) {
        super(name, handCards, money);
    }

    @Override
    public boolean canDrawMore() {
        return !isBust();
    }
}
