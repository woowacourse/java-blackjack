package blackjack.domain.player.exception;

import blackjack.common.exception.CustomException;

public class InvalidChallengerNumberException extends CustomException {

    private static final String MESSAGE = "도전자의 숫자는 10명 이하여야 합니다.";

    public InvalidChallengerNumberException() {
        super(MESSAGE);
    }
}
