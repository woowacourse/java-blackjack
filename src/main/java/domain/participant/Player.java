package domain.participant;

import exception.BlackJackException;

public class Player extends BlackjackParticipant {

    private static final String INVALID_PLAYER_NAME = "일반 플레이어는 딜러일 수 없습니다.";

    public Player(String name) {
        super(name);
        validatePlayerName(name);
    }

    public Player(String name, int bet) {
        super(name, bet);
    }

    private void validatePlayerName(String name) {
        if (name.equals(BlackjackParticipant.dealerName())) {
            throw new BlackJackException(INVALID_PLAYER_NAME);
        }
    }

    @Override
    public boolean isDrawable() {
        return !isBust();
    }

    @Override
    public double earnMoney(BlackjackParticipant otherPlayer) {
        return calculateWinningMoney(otherPlayer.cardSum()) - betMoney();
    }
}
