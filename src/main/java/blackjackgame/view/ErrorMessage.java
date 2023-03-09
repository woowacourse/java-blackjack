package blackjackgame.view;

public enum ErrorMessage {
    INVALID_PLAYER_SIZE("게임을 시작하기 위해서는 최소 1명, 최대 5명의 플레이어가 필요합니다."),
    INVALID_PLAYER_NAME_FORMAT("플레이어 이름 형식에 맞지 않는 이름이 존재합니다."),
    DUPLICATED_PLAYER_NAME_EXIST("중복되는 플레이어 이름이 존재합니다."),
    NO_MORE_CARD("카드가 존재하지 않습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
