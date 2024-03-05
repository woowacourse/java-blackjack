package domain;

public record Name(String name) {

    public Name {
        validateNotBlank(name);
    }

    private void validateNotBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("플레이어 이름을 입력해주세요. 예) 포비, 호티, 제우스");
        }
    }
}
