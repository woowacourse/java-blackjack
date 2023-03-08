package blackjack.domain.exception;

import blackjack.constants.ErrorCode;

public class NoMoreCardException extends CustomException {

    public NoMoreCardException(ErrorCode errorCode) {
        super(errorCode);
    }
}
