package blackjack.view.exception;

import blackjack.constants.ErrorCode;
import blackjack.domain.exception.CustomException;

public class MessageDoesNotExistException extends CustomException {

    public MessageDoesNotExistException(ErrorCode errorCode) {
        super(errorCode);
    }
}
