package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class NameTest {

    private static final int UPPER_BOUND = 10;
    private static final String RESTRICT = "딜러";

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "   "})
    void 이름이_존재하지_않으면_예외를_던진다(final String input) {
        assertThatThrownBy(() -> new Name(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 존재해야 합니다. 현재 이름: " + input);
    }

    @Test
    void 이름은_1자_이상_10자_이하가_아니라면_예외를_던진다() {
        final String input = "abcdeabcdef";

        assertThatThrownBy(() -> new Name(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 " + UPPER_BOUND + "글자 이하여야 합니다. 현재 이름: " + input);
    }

    @Test
    void 이름이_딜러_라면_예외를_던진다() {
        final String input = "딜러";

        assertThatThrownBy(() -> new Name(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 " + RESTRICT + "일 수 없습니다. 현재 이름: " + input);
    }
}
