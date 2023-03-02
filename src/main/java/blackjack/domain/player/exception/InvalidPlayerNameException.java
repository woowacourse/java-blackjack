package blackjack.domain.player.exception;

import blackjack.common.exception.CustomException;

public class InvalidPlayerNameException extends CustomException {

    private static final String MESSAGE = "플레이어의 이름은 '딜러'이면 안됩니다.";

    public InvalidPlayerNameException() {
        super(MESSAGE);
    }
}
