package exception;

import constants.ErrorCode;

public class InvalidSeparatorException extends CustomException{

    public InvalidSeparatorException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
