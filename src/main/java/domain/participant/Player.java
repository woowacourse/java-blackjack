package domain.participant;

import domain.card.Card;
import java.util.List;

public class Player extends Participant {

    private static final int UPPER_BOUND_OF_DRAWABLE_SCORE = 21;

    private final BettingMoney bettingMoney;
    private Decision decision;

    public Player(Name name, Hand hand, BettingMoney bettingMoney) {
        super(name, hand);
        this.bettingMoney = bettingMoney;
        this.decision = Decision.HIT;
    }

    public void stand() {
        decision = Decision.STAND;
    }

    @Override
    public boolean isDrawable() {
        return decision == Decision.HIT &&
                score() < UPPER_BOUND_OF_DRAWABLE_SCORE;
    }

    @Override
    public List<Card> initialHand() {
        return hand.cards();
    }

    public int bettingMoney() {
        return bettingMoney.value();
    }

}
