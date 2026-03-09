package domain;

import domain.vo.Name;

public class Dealer extends Participant{
    private static final int DEALER_HIT_CONDITION = 17;

    public Dealer() {
        this.name = new Name("딜러");
    }

    public boolean isDealerHit(){
        if (getMyScore() < DEALER_HIT_CONDITION) {
            return true;
        }

        return false;
    }
}
