package view.message;

import constants.ErrorCode;
import exception.MessageDoesNotExistException;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ErrorCodeMessage {

    NOT_EXIST_MESSAGE(ErrorCode.NOT_EXIST_MESSAGE, "해당 메시지가 없습니다."),
    INVALID_SEPARATOR(ErrorCode.INVALID_SEPARATOR, "유효하지 않은 구분자입니다."),
    INVALID_INPUT(ErrorCode.INVALID_INPUT, "유효하지 않은 입력입니다."),
    INVALID_SIZE(ErrorCode.INVALID_SIZE, "유효하지 않은 참여자 수입니다."),
    DUPLICATE_NAME(ErrorCode.DUPLICATE_NAME, "중복된 이름은 사용할 수 없습니다."),
    RESERVED_NAME(ErrorCode.RESERVED_NAME, "이름은 딜러일 수 없습니다."),
    BLANK_VALUE(ErrorCode.BLANK_VALUE, "이름은 공백일 수 없습니다."),
    INVALID_BET_AMOUNT(ErrorCode.INVALID_BET_AMOUNT, "유효하지 않은 배팅 금액입니다."),
    EMPTY_CARD(ErrorCode.EMPTY_CARD, "뽑을 수 있는 카드가 없습니다."),
    INVALID_COMMAND(ErrorCode.INVALID_COMMAND, "y 또는 n을 입력해주세요"),
    ;

    private static final Map<ErrorCode, ErrorCodeMessage> SUIT_MESSAGE = Arrays.stream(values())
            .collect(Collectors.toMap(ErrorCodeMessage::getCode, Function.identity()));


    private final ErrorCode errorCode;
    private final String message;

    ErrorCodeMessage(final ErrorCode errorCode, final String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public static ErrorCodeMessage from(ErrorCode errorCode) {
        if (SUIT_MESSAGE.containsKey(errorCode)) {
            return SUIT_MESSAGE.get(errorCode);
        }
        throw new MessageDoesNotExistException(ErrorCode.NOT_EXIST_MESSAGE);
    }

    private ErrorCode getCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
