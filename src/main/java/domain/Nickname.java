package domain;

public record Nickname(String value) {
    public Nickname {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("닉네임은 빈 값으로 설정할 수 없습니다.");
        }
    }
}
