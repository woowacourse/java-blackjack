package blackjack.domain.user.exception;

import blackjack.constants.ErrorCode;
import blackjack.domain.exception.CustomException;

public class ReservedPlayerNameException extends CustomException {

    public ReservedPlayerNameException(ErrorCode errorCode) {
        super(errorCode);
    }
}
