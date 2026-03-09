package blackjack.domain;

public record UserName(
        String name
) {

    private static final int MAX_LENGTH = 5;

    public UserName {
        name = name.strip();
        validate(name);
    }

    private void validate(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("플레이어 이름은 공백이 될 수 없습니다.");
        }
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("플레이어 이름은 5자가 넘을 수 없습니다.");
        }
    }
}
