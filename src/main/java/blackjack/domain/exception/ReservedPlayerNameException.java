package blackjack.domain.exception;

import blackjack.constants.ErrorCode;

public class ReservedPlayerNameException extends CustomException {

    public ReservedPlayerNameException(ErrorCode errorCode) {
        super(errorCode);
    }
}
