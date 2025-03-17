package domain.participant;

import domain.blackjackgame.TrumpCard;
import java.util.List;

public class Dealer extends BlackjackParticipant {

    private static final int DEALER_STOP_HIT_STANDARD = 17;

    public Dealer(List<TrumpCard> cards) {
        super(BlackjackParticipant.dealerName(), cards);
    }

    @Override
    public boolean isDrawable() {
        int sum = calculateCardSum();
        return sum < DEALER_STOP_HIT_STANDARD;
    }
}
