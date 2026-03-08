package domain.participants;

import domain.card.Hand;
import domain.stategy.HitStrategy;

public class Player extends Participant {
    private final HitStrategy hitStrategy;

    public Player(String name, Hand hand, HitStrategy hitStrategy) {
        super(name, hand);
        this.hitStrategy = hitStrategy;
    }
    public boolean needToHit(){
        return hitStrategy.needToHit(getScore());
    }
}
