package domain.participants;

public record PlayerName(String username) {
    public PlayerName {
        validateUsername(username);
    }

    private void validateUsername(String username) {
        if (username.isBlank()) {
            throw new IllegalArgumentException("이름은 비어있을 수 없습니다.");
        }
    }
}
