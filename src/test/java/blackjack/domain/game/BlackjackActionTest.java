package blackjack.domain.game;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class BlackjackActionTest {

    @ParameterizedTest
    @CsvSource(value = {"y, HIT", "n, STAY"})
    void y_n의_값을_통해_명령어를_찾을_수_있다(String expression, BlackjackAction expected) {
        final BlackjackAction actual = BlackjackAction.from(expression);
        assertThat(actual).isEqualTo(expected);
    }
}
