package domain;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ExceptionCode  {
    PLAYER_INVALID_NAME("PLAYER_INVALID_NAME"),
    DUPLICATE_PLAYERS_NAME("DUPLICATE_PLAYERS_NAME"),
    OUT_OF_RANGE_PLAYER_COUNT("OUT_OF_RANGE_PLAYER_COUNT"),
    NOT_CONTAINS_COMMA_PLAYER_NAME("NOT_CONTAINS_COMMA_PLAYER_NAME"),
    OUT_OF_RANGE_PLAYER_NAME_LENGTH("OUT_OF_RANGE_PLAYER_NAME_LENGTH"),
    NAME_IS_EMPTY("NAME_IS_EMPTY"),
    NAME_IS_NULL("NAME_IS_NULL"),
    NO_DEALER("NO_DEALER"),
    INVALID_HIT_OR_STAY("INVALID_HIT_OR_STAY"),
    WRONG_ACCESS("WRONG_ACCESS"),
    LEAK_BET_AMOUNT("LEAK_BET_AMOUNT"),
    TYPE_MISS_MATCH_BET_AMOUNT("TYPE_MISS_MATCH_BET_AMOUNT");

    private final String exceptionCode;

    private static final Map<String, ExceptionCode> exceptionCodes =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(ExceptionCode::getExceptionCode, Function.identity())));

    public static ExceptionCode getExceptionCodeName(String exceptionCode) {
        return exceptionCodes.get(exceptionCode);
    }
    ExceptionCode(final String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }
}
