package exception;

import constants.ErrorCode;

public class NoMoreCardException extends CustomException{

    public NoMoreCardException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
