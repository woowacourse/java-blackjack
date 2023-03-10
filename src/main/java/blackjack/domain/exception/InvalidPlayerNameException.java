package blackjack.domain.exception;

import blackjack.constants.ErrorCode;

public class InvalidPlayerNameException extends CustomException {

    public InvalidPlayerNameException(ErrorCode errorCode) {
        super(errorCode);
    }
}
