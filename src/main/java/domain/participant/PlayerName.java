package domain.participant;

public record PlayerName(String name) {
    public PlayerName {
        validateNameIsNotBlank(name);
    }

    private void validateNameIsNotBlank(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("빈 문자열은 이름이 될 수 없습니다.");
        }
    }
}
