package blackjack.model.participant;

public record Name(
        String value
) {
    public Name {
        validateBlank(value);

        value = value.trim();
    }

    public String get() {
        return value;
    }

    private void validateBlank(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("플레이어 이름은 null이거나 비어 있을 수 없습니다.");
        }
    }
}
