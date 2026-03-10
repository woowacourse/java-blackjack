package domain.participants;

import domain.card.Hand;
import domain.hitStrategy.HitStrategy;

public class Player extends Participant {
    private final HitStrategy hitStrategy;
    //아마 여기에 금액추가.

    public Player(String name, Hand hand, HitStrategy hitStrategy) {
        super(name, hand);
        this.hitStrategy = hitStrategy;
    }

    public boolean needToHit() {
        return hitStrategy.needToHit(getScore());
    }
}
