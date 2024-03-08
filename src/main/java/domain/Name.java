package domain;

public record Name(String name) {
    static final int MIN_NAME_LENGTH = 1;
    static final int MAX_NAME_LENGTH = 5;
    static final String NAME_LENGTH_MESSAGE
            = String.format("이름은 %d에서 %d글자 사이여야 합니다", MIN_NAME_LENGTH, MAX_NAME_LENGTH);
    static final String DEALER_NAME_MESSAGE = "딜러라는 이름은 사용할 수 없습니다.";

    public Name {
        validateNotDealerName(name);
        validateLength(name);
    }

    private void validateLength(String name) {
        if (name.length() < MIN_NAME_LENGTH || MAX_NAME_LENGTH < name.length()) {
            throw new IllegalArgumentException(NAME_LENGTH_MESSAGE);
        }
    }

    private void validateNotDealerName(String name) {
        if ("딜러".equals(name)) {
            throw new IllegalArgumentException(DEALER_NAME_MESSAGE);
        }
    }
}
