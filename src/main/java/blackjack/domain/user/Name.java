package blackjack.domain.user;

public class Name {

    static final String BLANK_NAME_EXCEPTION_MESSAGE = "이름은 공백일 수 없습니다.";
    static final String NAME_IS_DEALER_NAME_EXCEPTION_MESSAGE = "플레이어의 이름은 '딜러'일 수 없습니다.";

    private final String value;

    public Name(final String name) {
        validateBlank(name);
        validateIsDealerName(name);
        this.value = name;
    }

    private void validateIsDealerName(final String name) {
        if (Dealer.DEALER_NAME.equals(name)) {
            throw new IllegalArgumentException(NAME_IS_DEALER_NAME_EXCEPTION_MESSAGE);
        }
    }

    private void validateBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(BLANK_NAME_EXCEPTION_MESSAGE);
        }
    }

    public String getValue() {
        return value;
    }
}
