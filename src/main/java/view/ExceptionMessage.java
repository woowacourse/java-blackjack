package view;

import domain.ExceptionCode;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ExceptionMessage {
    INVALID_DRAWING_CARD_REQUEST(ExceptionCode.INVALID_HIT_OR_STAY,"입력은 y 혹은 n이어야 합니다."),
    INVALID_NUMBER_OF_PLAYER(ExceptionCode.OUT_OF_RANGE_PLAYER_COUNT,"플레이어의 수는 1명 이상, 7명 이하여야 합니다."),
    NAME_IS_EMPTY(ExceptionCode.NAME_IS_EMPTY,"이름은 공백일 수 없습니다."),
    NAME_IS_NULL(ExceptionCode.NAME_IS_NULL,"이름은 null일 수 없습니다."),
    NAME_IS_DEALER(ExceptionCode.PLAYER_INVALID_NAME,"이름은 '딜러'일 수 없습니다."),
    NAME_IS_DUPLICATED(ExceptionCode.DUPLICATE_PLAYERS_NAME,"이름이 중복되었습니다."),
    NAME_CONTAINS_COMMA(ExceptionCode.NOT_CONTAINS_COMMA_PLAYER_NAME,"이름에 쉼표(,)가 들어갈 수 없습니다."),
    INVALID_NAME_LENGTH(ExceptionCode.OUT_OF_RANGE_PLAYER_NAME_LENGTH,"이름의 길이는 10자 이하여야 합니다."),
    NO_DEALER(ExceptionCode.NO_DEALER,"딜러가 존재하지 않습니다."),
    WRONG_ACCESS(ExceptionCode.WRONG_ACCESS, "플레이어가 접근할 수 없는 메서드입니다."),
    LEAK_BET_AMOUNT(ExceptionCode.LEAK_BET_AMOUNT, "배팅 금액은 최소 1000원 입니다."),
    TYPE_MISS_MATCH_BET_AMOUNT(ExceptionCode.TYPE_MISS_MATCH_BET_AMOUNT, "배팅 금액은 정수만 가능합니다.");

    private final ExceptionCode exceptionCode;
    private final String message;

    ExceptionMessage(final ExceptionCode exceptionCode, final String message) {
        this.exceptionCode = exceptionCode;
        this.message = message;
    }

    private static final Map<ExceptionCode, String> exceptionMessage =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(ExceptionMessage::getExceptionCode, ExceptionMessage::getMessage)));

    public static String getExceptionMessage(ExceptionCode exceptionCode) {
        return exceptionMessage.get(exceptionCode);
    }

    ExceptionCode getExceptionCode() {
        return exceptionCode;
    }

    String getMessage() {
        return message;
    }
}
