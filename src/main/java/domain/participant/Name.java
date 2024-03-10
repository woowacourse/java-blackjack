package domain.participant;

public record Name(String value) {
    public Name {
        if (value.isBlank()) {
            throw new IllegalArgumentException("플레이어 이름을 입력해주세요. 예) 포비, 호티, 제우스");
        }
    }
}
