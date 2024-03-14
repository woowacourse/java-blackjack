package exception;

import constants.ErrorCode;

public class OutOfRangeBetAmount extends CustomException {

    public OutOfRangeBetAmount(final ErrorCode errorCode) {
        super(errorCode);
    }
}
