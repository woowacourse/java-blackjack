package blackJack.domain.participant;

import blackJack.domain.result.WinDrawLose;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_MAXIMUM_RECEIVE_CARD_SCORE = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    public WinDrawLose isWin(Player player) {
        final WinDrawLose resultByPlayer = WinDrawLose.calculateWinDrawLose(player, this);
        return WinDrawLose.swapResult(resultByPlayer);
    }

    @Override
    public boolean hasNextTurn() {
        return this.getScore() <= DEALER_MAXIMUM_RECEIVE_CARD_SCORE;
    }
}
