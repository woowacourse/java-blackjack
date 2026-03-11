package domain;

import static exception.ErrorMessage.PLAYER_NAME_LENGTH_ERROR;

public record Name(
        String name
) {
    // TODO: 검증 테스트 추가
    public Name(final String name) {
        if (name.length() < 2 || name.length() > 10) { // FIXME: 하드코딩된 길이 하한, 상한 값 상수화
            throw new IllegalArgumentException(PLAYER_NAME_LENGTH_ERROR.getMessage());
        }
        this.name = name;
    }
}
