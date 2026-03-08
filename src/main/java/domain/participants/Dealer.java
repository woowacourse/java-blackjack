package domain.participants;

import domain.card.Hand;
import domain.stategy.HitStrategy;

//추상 클래스
public class Dealer extends Participant {
    private static final String NAME = "딜러";

    private final HitStrategy hitStrategy;

    public Dealer(Hand hand, HitStrategy hitStrategy) {
        super(NAME,hand);
        this.hitStrategy = hitStrategy;
    }

    public boolean needsToHit() {
        return hitStrategy.needToHit(getScore());
    }
}
