package blackjack.domain.game.exception;

import blackjack.constants.ErrorCode;
import blackjack.domain.exception.CustomException;

public class InvalidMoneyValueException extends CustomException {
    public InvalidMoneyValueException(ErrorCode errorCode) {
        super(errorCode);
    }
}
