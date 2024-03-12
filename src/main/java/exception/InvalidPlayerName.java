package exception;

import constants.ErrorCode;

public class InvalidPlayerName extends CustomException{

    public InvalidPlayerName(final ErrorCode errorCode) {
        super(errorCode);
    }
}
