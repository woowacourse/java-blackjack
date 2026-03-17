package domain.participant;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {

    private static final int STAND_SCORE = 17;

    public Dealer() {
        super(new Name("딜러"), new Hand());
    }
    
    @Override
    public boolean canHit() {
        return getScore() < STAND_SCORE;
    }

    public List<String> firstCardNames() {
        List<String> dealerCard = new ArrayList<>(createCardNames());
        dealerCard.removeLast();
        return dealerCard;
    }

}
