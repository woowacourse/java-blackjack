package blackjack.domain.game;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class GameCommandTest {

    @ParameterizedTest
    @CsvSource(value = {"y, YES", "n, NO"})
    void y_n의_값을_통해_명령어를_찾을_수_있다(String expression, GameCommand expected) {
        final GameCommand actual = GameCommand.from(expression);
        assertThat(actual).isEqualTo(expected);
    }
}
