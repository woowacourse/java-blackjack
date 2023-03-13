package blackjack.domain.participant;

public class Name {

    private static final String EMPTY_NAME_MASSAGE = "빈 이름은 허용되지 않습니다.";
    private final String value;

    public Name(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_NAME_MASSAGE);
        }
    }

    public String getValue() {
        return this.value;
    }
}
