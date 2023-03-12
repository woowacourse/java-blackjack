package blackjack.view.message;

import blackjack.constants.ErrorCode;
import blackjack.view.exception.MessageDoesNotExistException;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ErrorCodeMessage {
    DUPLICATE_NAME(ErrorCode.DUPLICATE_NAME, "중복된 이름은 사용할 수 없습니다."),
    RESERVED_NAME(ErrorCode.RESERVED_NAME, "이름은 딜러일 수 없습니다"),
    NOT_EXIST_MESSAGE(ErrorCode.NOT_EXIST_MESSAGE, "해당 메세지가 없습니다."),
    BLANK_NAME(ErrorCode.BLANK_NAME, "이름은 공백일 수 없습니다."),
    EMPTY_CARD(ErrorCode.EMPTY_CARD, "뽑을 수 있는 카드가 없습니다."),
    INVALID_COMMAND(ErrorCode.INVALID_COMMAND, "Y 또는 N을 입력해주세요."),
    INVALID_MONEY_UNIT(ErrorCode.INVALID_MONEY_UNIT, "금액은 1000원 단위로 입력 가능합니다."),
    INVALID_MONEY_BOUND(ErrorCode.INVALID_MONEY_BOUND, "금액은 10,000,000원 이하만 입력 가능합니다."),
    ;

    private static final Map<ErrorCode, ErrorCodeMessage> SUIT_MESSAGE = Arrays.stream(values())
            .collect(Collectors.toMap(ErrorCodeMessage::getCode, Function.identity()));

    private final ErrorCode errorCode;

    private final String message;

    ErrorCodeMessage(ErrorCode errorCode, String message) {
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
