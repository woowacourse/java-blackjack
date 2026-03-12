package common;

public enum ErrorMessage {
    HIT_OR_STAND_VALUE_MIS_MATCH("y 혹은 n만 입력 가능합니다."),
    DRAW_CARD_OUT_OF_RANGE("남은 카드 수 만큼만 선택 가능"),
    UNSUPPORTED_OPERATION_MESSAGE("해당 객체[%s]에서는 지원하지 않는 메서드입니다 "),
    NOT_ALLOW_EMPTY_INPUT("공백은 허용되지 않습니다"),
    ONLY_KO_AND_ENG("이름은 영어 또는 한국어만 가능합니다: "),
    NAME_UNIQUENESS_ERR("이름은 중복되면 안됩니다"),
    PLAYER_NOT_FOUND("해당 플레이어를 찾을 수 없습니다"),
    NO_MORE_PLAYABLE_PLAYER("더 이상 게임을 진행할 수 있는 플레이어가 없습니다."),
    NOT_ALLOW_METHOD_CALL("현재 상태에서 허용되지 않는 메소드 호출입니다"),
    MAX_PLAYER_ERROR("최대 인원을 초과했습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
