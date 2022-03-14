package blackJack.domain.participant;

import blackJack.domain.result.WinDrawLose;

public class Player extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final String ERROR_MESSAGE_PROHIBIT_NAME = "플레이어의 이름은 '딜러'일 수 없습니다.";

    public Player(String name) {
        super(name);
        validateProhibitName(name);
    }

    private void validateProhibitName(String name) {
        if (name.equals(DEALER_NAME)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PROHIBIT_NAME);
        }
    }

    public WinDrawLose isWin(Dealer dealer) {
        return WinDrawLose.calculateWinDrawLose(this, dealer);
    }

    @Override
    public boolean hasNextTurn() {
        return !isBust();
    }
}
