package domain;

import except.BlackJackException;

public class Player extends BlackjackParticipant {

    private final String PLAYER_CANNOT_BE_DEALER = "플레이어는 딜러일 수 없습니다";

    public Player(String name) {
        super(name);
        validatePlayer();
    }

    private void validatePlayer() {
        if (isDealer()) {
            throw new BlackJackException(PLAYER_CANNOT_BE_DEALER);
        }
    }
}
