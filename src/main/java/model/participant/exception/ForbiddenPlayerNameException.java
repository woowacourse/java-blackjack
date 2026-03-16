package model.participant.exception;

public class ForbiddenPlayerNameException extends IllegalArgumentException {
    public ForbiddenPlayerNameException(String playerName) {
        super("플레이어는 '" + playerName + "'라는 이름을 사용할 수 없습니다.");
    }
}
