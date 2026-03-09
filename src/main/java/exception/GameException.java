package exception;

import constant.ErrorCode;

public class GameException extends IllegalArgumentException {
    private final ErrorCode errorCode;

    public GameException(ErrorCode errorCode) {
        super("[" + errorCode.getCode() + "] " + errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}

