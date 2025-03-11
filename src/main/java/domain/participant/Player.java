package domain.participant;

import exception.BlackJackException;

public class Player extends BlackjackParticipant {

    private static final String INVALID_PLAYER_NAME = "일반 플레이어는 딜러일 수 없습니다.";

    public Player(String name) {
        super(name);
        validatePlayerName(name);
    }

    private void validatePlayerName(String name) {
        if (name.equals(BlackjackParticipant.dealerName())) {
            throw new BlackJackException(INVALID_PLAYER_NAME);
        }
    }

    @Override
    public boolean isDrawable() {
        int sum = calculateCardSum();
        return !isBUST(sum);
    }
}
