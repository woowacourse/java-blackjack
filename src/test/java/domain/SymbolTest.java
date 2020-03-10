package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class SymbolTest {
    @Test
    void of() {
        assertThat(Symbol.of("A") == Symbol.ACE).isTrue();
        assertThat(Symbol.of(2) == Symbol.TWO).isTrue();
        assertThat(Symbol.of(3) == Symbol.THREE).isTrue();
        assertThat(Symbol.of(4) == Symbol.FOUR).isTrue();
        assertThat(Symbol.of(5) == Symbol.FIVE).isTrue();
        assertThat(Symbol.of(6) == Symbol.SIX).isTrue();
        assertThat(Symbol.of(7) == Symbol.SEVEN).isTrue();
        assertThat(Symbol.of(8) == Symbol.EIGHT).isTrue();
        assertThat(Symbol.of(9) == Symbol.NINE).isTrue();
        assertThat(Symbol.of(10) == Symbol.TEN).isTrue();
        assertThat(Symbol.of("J") == Symbol.JACK).isTrue();
        assertThat(Symbol.of("Q") == Symbol.QUEEN).isTrue();
        assertThat(Symbol.of("K") == Symbol.KING).isTrue();
    }

    @Test
    void of_존재하지_않는_심볼() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Symbol.of("1"));
    }
}
