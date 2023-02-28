package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class NameTest {

    @Test
    void 이름이_존재하지_않으면_예외를_던진다() {
        final String input = "   ";

        assertThatThrownBy(() -> new Name(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "abcdeabcdea"})
    void 이름은_1자_이상_10자_이하가_아니라면_예외를_던진다(final String input) {
        assertThatThrownBy(() -> new Name(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 이름이_딜러_라면_예외를_던진다() {
        final String input = "딜러";

        assertThatThrownBy(() -> new Name(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

class Name {

    private static final int UPPER_BOUND = 10;
    private static final String RESTRICT = "딜러";

    private final String value;

    public Name(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(final String value) {
        validateBlank(value);
        validateLength(value);
        validateRestrictWord(value);
    }

    private void validateBlank(final String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException("이름은 존재해야 합니다. 현재 이름: " + value);
        }
    }

    private void validateLength(final String value) {
        if (value.length() > UPPER_BOUND) {
            throw new IllegalArgumentException("이름은 " + UPPER_BOUND + "글자 이하여야 합니다. 현재 이름: " + value);
        }
    }

    private void validateRestrictWord(final String value) {
        if (value.equals(RESTRICT)) {
            throw new IllegalArgumentException("이름은 " + RESTRICT + "일 수 없습니다. 현재 이름: " + value);
        }
    }
}
