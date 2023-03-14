package blackjack.domain.card.exception;

import blackjack.constants.ErrorCode;
import blackjack.domain.exception.CustomException;

public class NoMoreCardException extends CustomException {

    public NoMoreCardException(ErrorCode errorCode) {
        super(errorCode);
    }
}
