package model.participant.exception;

import java.util.List;

public class DuplicatePlayerNameException extends IllegalArgumentException {
    public DuplicatePlayerNameException(List<String> names) {
        super("플레이어의 이름은 서로 중복될 수 없습니다. 현재 이름 목록: " + String.join(",", names));
    }
}
