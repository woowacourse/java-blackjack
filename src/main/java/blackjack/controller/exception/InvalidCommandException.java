package blackjack.controller.exception;

import blackjack.constants.ErrorCode;
import blackjack.domain.exception.CustomException;

public class InvalidCommandException extends CustomException {

    public InvalidCommandException(ErrorCode errorCode) {
        super(errorCode);
    }
}
