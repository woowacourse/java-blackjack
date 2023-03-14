package blackjack.domain.betting;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BettingTest {

    @Test
    void 베팅이_100원_미만이면_예외를_던진다() {
        final int value = 99;

        assertThatThrownBy(() -> new Betting(value)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 베팅이_100만원_초과이면_예외를_던진다() {
        final int value = 1_000_001;

        assertThatThrownBy(() -> new Betting(value)).isInstanceOf(IllegalArgumentException.class);
    }
}
