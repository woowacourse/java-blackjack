package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayerNameTest {

    @Test
    void 이름이_공백이면_예외를_던진다() {
        final String input = "   ";

        assertThatThrownBy(() -> new PlayerName(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "abcdeabcdea"})
    void 이름은_1자_이상_10자_이하가_아니라면_예외를_던진다(final String input) {
        assertThatThrownBy(() -> new PlayerName(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 이름이_딜러라면_예외를_던진다() {
        final String input = "딜러";

        assertThatThrownBy(() -> new PlayerName(input)).isInstanceOf(IllegalArgumentException.class);
    }
}
