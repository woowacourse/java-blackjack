package blackjack.domain.participant;

public final class Name {
    private final String cleaned;

    public Name(final String rawValue) {
        validate(rawValue);
        this.cleaned = getStripped(rawValue);
    }

    private void validate(final String rawValue) {
        if (rawValue == null || rawValue.isBlank()) {
            throw new IllegalArgumentException("이름은 null 이거나 empty 일 수 없습니다.");
        }
    }

    private String getStripped(final String rawValue) {
        return rawValue.strip();
    }

    public String getCleaned() {
        return cleaned;
    }
}
