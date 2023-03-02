package blackjack.domain.participant;

public class Name {

    private final String value;

    public Name(final String value) {
        validateLengthOfName(value);
        this.value = value;
    }

    private void validateLengthOfName(final String value) {
        if (value.length() < 1 || value.length() > 5) {
            throw new IllegalArgumentException("이름의 길이는 1이상 5이하만 가능합니다.");
        }
    }

    public String getValue() {
        return value;
    }
}
