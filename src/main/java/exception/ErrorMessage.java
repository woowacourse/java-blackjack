package exception;

import java.text.DecimalFormat;

import static constant.BlackjackConstant.*;

public enum ErrorMessage {
    PLAYER_NAME_LENGTH_ERROR(String.format(
            "플레이어의 이름은 %s글자 이상 %s글자 이하여야 합니다.", NAME_MINIMUM_LENGTH, NAME_MAX_LENGTH)),
    INPUT_EMPTY_ERROR("특정 값을 입력해주세요."),
    INVALID_HIT_STAND_INPUT_ERROR("y 또는 n만 입력 가능합니다."),
    DEALER_NOT_FOUND_ERROR("딜러가 존재하지 않습니다."),
    PLAYER_COUNT_OUT_OF_RANGE(String.format(
            "참여자수는 최소%s명 최대 %s명이어야 합니다.", MINIMUM_PLAYER_BOUND, MAXIMUM_PLAYER_BOUND)),
    INVALID_BETTING_AMOUNT_FORMAT_ERROR("베팅 금액은 숫자로만 입력 가능합니다."),
    BETTING_LIMIT_OUT_OF_BOUNDS_ERROR(String.format("1 ~ 베팅상한(%s) 사이로 베팅해주세요.",
            new DecimalFormat("###,###").format(BETTING_LIMIT)))
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
