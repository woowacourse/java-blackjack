package blackjack.domain.exception;

import blackjack.constants.ErrorCode;

public class DuplicatePlayerNameException extends CustomException {

    public DuplicatePlayerNameException(ErrorCode errorCode) {
        super(errorCode);
    }
}
