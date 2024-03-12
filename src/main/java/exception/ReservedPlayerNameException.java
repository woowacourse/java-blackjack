package exception;

import constants.ErrorCode;

public class ReservedPlayerNameException extends CustomException {

    public ReservedPlayerNameException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
