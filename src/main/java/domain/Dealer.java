package domain;

import java.util.ArrayList;

public class Dealer extends Participant{
    private static final int burstThreshold = 17;

    public Dealer(Name name){
        super(name);
    }

    public boolean canReceiveCard() {
        return cards.canReceiveCard(burstThreshold);
    }
}
