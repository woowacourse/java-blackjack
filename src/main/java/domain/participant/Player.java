package domain.participant;

import domain.BettingAmount;
import domain.card.Cards;

public class Player extends Participant {

    private final BettingAmount bettingAmount;

    public Player(ParticipantName participantName, BettingAmount bettingAmount, Cards cards) {
        super(participantName, cards);
        this.bettingAmount = bettingAmount;
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public boolean shouldHit() {
        return !cards.isBlackjackScoreExceeded();
    }

    @Override
    public int getBettingAmount() {
        return bettingAmount.getAmount();
    }
}
