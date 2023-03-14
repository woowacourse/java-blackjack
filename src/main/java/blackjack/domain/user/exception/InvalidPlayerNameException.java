package blackjack.domain.user.exception;

import blackjack.constants.ErrorCode;
import blackjack.domain.exception.CustomException;

public class InvalidPlayerNameException extends CustomException {

    public InvalidPlayerNameException(ErrorCode errorCode) {
        super(errorCode);
    }
}
