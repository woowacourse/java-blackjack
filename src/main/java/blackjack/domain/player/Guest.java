package blackjack.domain.player;

import blackjack.domain.HitFlag;
import blackjack.domain.card.Cards;
import blackjack.domain.strategy.HitStrategy;
import java.util.ArrayList;

public class Guest extends AbstractPlayer {
    private final HitStrategy hitStrategy;

    public Guest(String name, HitStrategy hitStrategy) {
        super(name, new Cards(new ArrayList<>()));
        this.hitStrategy = hitStrategy;
    }

    @Override
    public Cards getShowCards() {
        return getCards();
    }

    @Override
    public HitFlag checkHitFlag() {
        return hitStrategy.getHitFlag(this);
    }
}
