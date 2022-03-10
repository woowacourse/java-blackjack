package blackjack;

import java.util.regex.Pattern;

public class Entry extends Player {
    private static final String ERROR_BLANK = "[ERROR] 이름은 공백일 수 없습니다.";
    private static final String ERROR_MAX_LENGTH = "[ERROR] 이름은 15자 이하로 입력해주세요.";
    private static final String ERROR_CONTAINS_NUMBER = "[ERROR] 이름에 숫자는 포함될 수 없습니다.";
    private static final String ERROR_CONTAINS_SIGN = "[ERROR] 이름에 기호는 포함될 수 없습니다.";

    private static final String REGEX_NAME_CONTAINS_NUMBER = "^\\D*[0-9]+\\D*$";
    private static final String REGEX_NAME_CONTAINS_SIGN = "^\\D*[!\"#$%&'()*+,./:;<=>?@\\\\^_`{|}~-]+\\D*$";

    private static final int MAX_LENGTH = 15;

    private Entry(String name, Deck deck) {
        super(name, deck);
        validate(name);
    }

    private void validate(String name) {
        checkBlank(name);
        checkLength(name);
        checkNumberIn(name);
        checkSignIn(name);
    }

    private void checkBlank(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(ERROR_BLANK);
        }
    }

    private void checkLength(String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(ERROR_MAX_LENGTH);
        }
    }

    private void checkNumberIn(String name) {
        if (Pattern.matches(REGEX_NAME_CONTAINS_NUMBER, name)) {
            throw new IllegalArgumentException(ERROR_CONTAINS_NUMBER);
        }
    }

    private void checkSignIn(String name) {
        if (Pattern.matches(REGEX_NAME_CONTAINS_SIGN, name)) {
            throw new IllegalArgumentException(ERROR_CONTAINS_SIGN);
        }
    }

    public static class Builder extends Player.Builder {

        public Builder(String name) {
            super(name);
        }

        @Override
        public Entry build() {
            if (deck == null) {
                throw new RuntimeException(ERROR_DECK_IS_NULL);
            }
            return new Entry(this.name, this.deck);
        }
    }

    public boolean isBust() {
         return this.deck.isBust();
    }

    public void hit(TrumpCard card) {
        this.deck.add(card);
    }
}
