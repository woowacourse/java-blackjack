package blackjack.domain.vo.exception;

import blackjack.constants.ErrorCode;
import blackjack.domain.exception.CustomException;

public class InvalidScoreException extends CustomException {
    public InvalidScoreException(ErrorCode errorCode) {
        super(errorCode);
    }
}
