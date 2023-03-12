package blackjack.domain.participant;

import blackjack.domain.card.Hand;
import blackjack.domain.betting.BettingMoney;

public class Player extends Participant {

    private static final int BLACKJACK_MAX_NUMBER = 21;

    private final BettingMoney bettingMoney;

    public Player(ParticipantName participantName, Hand hand, BettingMoney bettingMoney) {
        super(participantName, hand);
        this.bettingMoney = bettingMoney;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

    @Override
    public boolean decideHit() {
        if (calculateCardNumber() < BLACKJACK_MAX_NUMBER) {
            return true;
        }
        return false;
    }
}
