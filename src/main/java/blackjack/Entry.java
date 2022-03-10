package blackjack;

import java.util.regex.Pattern;

public class Entry {
    private static final String ERROR_NULL = "[ERROR] 입력된 이름이 없습니다.";
    private static final String ERROR_BLANK = "[ERROR] 이름은 공백일 수 없습니다.";
    private static final String ERROR_MAX_LENGTH = "[ERROR] 이름은 15자 이하로 입력해주세요.";
    private static final String ERROR_CONTAINS_NUMBER = "[ERROR] 이름에 숫자는 포함될 수 없습니다.";
    private static final String ERROR_CONTAINS_SIGN = "[ERROR] 이름에 기호는 포함될 수 없습니다.";

    private static final String REGEX_NAME_CONTAINS_NUMBER = "^\\D*[0-9]+\\D*$";
    private static final String REGEX_NAME_CONTAINS_SIGN = "^\\D*[!\"#$%&'()*+,./:;<=>?@\\\\^_`{|}~-]+\\D*$";

    private static final int MAX_LENGTH = 15;

    private final String name;
    private final Deck deck;

    private Entry(String name, Deck deck) {
        validate(name);
        this.name = name.trim();
        this.deck = deck;
    }

    private void validate(String name) {
        checkNull(name);
        checkBlank(name);
        checkLength(name);
        checkNumberIn(name);
        checkSignIn(name);
    }

    private void checkNull(String name) {
        if (name == null) {
            throw new IllegalArgumentException(ERROR_NULL);
        }
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

    public static class Builder {
        private static final String ERROR_DECK_IS_NULL = "[ERROR] deck()을 먼저 호출해서 Deck을 초기화해주세요.";

        private final String name;

        private Deck deck;

        public Builder(String name) {
            this.name = name;
        }

        public Builder deck(TrumpCard card1, TrumpCard card2) {
            this.deck = new Deck(card1, card2);
            return this;
        }

        public Entry build() {
            if (deck == null) {
                throw new RuntimeException(ERROR_DECK_IS_NULL);
            }
            return new Entry(this.name, this.deck);
        }
    }
}
