package blackjack.domain.user.exception;

import blackjack.constants.ErrorCode;
import blackjack.domain.exception.CustomException;

public class DuplicatePlayerNameException extends CustomException {

    public DuplicatePlayerNameException(ErrorCode errorCode) {
        super(errorCode);
    }
}
