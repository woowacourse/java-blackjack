package blackjack.domain.betting;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BettingTest {

    @ParameterizedTest
    @ValueSource(ints = {1_000, 100_000_000})
    void 배팅_금액은_천원부터_일억원까지만_배팅할_수_있다(final int bettingAmount) {
        assertThatCode(() -> new Betting(bettingAmount))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {100_000_001, 2_000_000_000})
    void 배팅_금액이_일억원보다_크다면_예외를_던진다(final int bettingAmount) {
        assertThatThrownBy(() -> new Betting(bettingAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최대 배팅 금액은 1억원입니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {999, 0})
    void 배팅_금액이_천원보다_작다면_예외를_던진다(final int bettingAmount) {
        assertThatThrownBy(() -> new Betting(bettingAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최소 배팅 금액은 1000원입니다.");
    }
}
