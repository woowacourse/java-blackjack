package blackjack.domain.user;

public class Name {

    static final String BLANK_NAME_EXCEPTION_MESSAGE = "이름은 공백일 수 없습니다.";
    static final String NAME_EQUAL_DEALER_CODE_EXCEPTION_MESSAGE = "이름은 딜러코드(" + Dealer.DEALER_NAME_CODE + ") 와 같을 수 없습니다.";

    private final String value;

    public Name(String name) {
        validateBlank(name);
        validateIsNameDealerCode(name);
        this.value = name;
    }

    private void validateBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(BLANK_NAME_EXCEPTION_MESSAGE);
        }
    }

    private void validateIsNameDealerCode(final String name) {
        if (name.equals(Dealer.DEALER_NAME_CODE)) {
            throw new IllegalArgumentException(NAME_EQUAL_DEALER_CODE_EXCEPTION_MESSAGE);
        }
    }

    public String getValue() {
        return value;
    }
}
