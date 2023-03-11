package blackjack.domain.participant;

import blackjack.domain.game.WinningResult;

import java.math.BigDecimal;

public class Player extends Participant {

    private final Amount amount;

    private static final int BLACKJACK_MAX_NUMBER = 21;


    public Player(final ParticipantName participantName,final Amount amount) {
        super(participantName);
        this.amount = amount;
    }

    public BigDecimal calculateAmountByGameResult(WinningResult winningResult) {
        return amount.calculateAmountByResult(winningResult);
    }

    @Override
    public boolean decideHit() {
        return calculateCardNumber() < BLACKJACK_MAX_NUMBER;
    }
}
