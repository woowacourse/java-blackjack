package exception;

import constant.PlayerAction;
import constant.PolicyConstant;

public enum ErrorMessage {
    NAME_DUPLICATED("게임 참가자의 이름은 중복 되어선 안됩니다."),
    PLAYER_COUNT_OUT_OF_RANGE(
        String.format("게임 참가자의 수는 %d~%d명 사이여야 합니다.", PolicyConstant.PLAYER_MIN_COUNT, PolicyConstant.PLAYER_MAX_COUNT)),
    INVALID_YES_NO_INPUT(
        String.format("%s 또는 %s을 입력해야 합니다.", PlayerAction.HIT.getAction(), PlayerAction.STAND.getAction())),
    PLAYER_NAME_LENGTH_OUT_OF_RANGE(
        String.format("게임 참가자의 이름은 %d~%d글자 사이여야 합니다.", PolicyConstant.PLAYER_NAME_MIN_LENGTH,
            PolicyConstant.PLAYER_NAME_MAX_LENGTH)),
    PLAYER_NAME_BLANK("게임 참가자의 이름은 공백이 될 수 없습니다."),
    ;

    private static final String PREFIX = "[ERROR] ";

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
