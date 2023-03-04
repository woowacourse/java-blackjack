package view;

public enum ErrorMessage {
    INVALID_PLAYER_SIZE("플레이어는 최대 5명입니다."),
    NO_MORE_CARD("카드가 존재하지 않습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
