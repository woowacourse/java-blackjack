package exception;

import constants.ErrorCode;

public class InvalidBetAmountException extends CustomException {

    public InvalidBetAmountException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
