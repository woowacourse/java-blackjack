package exception;

import constants.ErrorCode;

public class DuplicatePlayerNameException extends CustomException {

    public DuplicatePlayerNameException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
