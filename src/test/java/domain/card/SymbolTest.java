package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class SymbolTest {
    @Test
    @DisplayName("심볼 of")
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
    @DisplayName("존재하지 않는 심볼")
    void of_not_exist_symbol() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Symbol.of("1"));
    }

    @Test
    @DisplayName("심볼이 Ace인지 확인")
    void isAce() {
        assertThat(Symbol.of("10").isAce()).isFalse();
        assertThat(Symbol.of("A").isAce()).isTrue();
    }

    @Test
    @DisplayName("심볼의 점수 확인")
    void getPoint() {
        assertThat(Symbol.of("A").getPoint()).isEqualTo(1);
        assertThat(Symbol.of("2").getPoint()).isEqualTo(2);
        assertThat(Symbol.of("3").getPoint()).isEqualTo(3);
        assertThat(Symbol.of("4").getPoint()).isEqualTo(4);
        assertThat(Symbol.of("5").getPoint()).isEqualTo(5);
        assertThat(Symbol.of("6").getPoint()).isEqualTo(6);
        assertThat(Symbol.of("7").getPoint()).isEqualTo(7);
        assertThat(Symbol.of("8").getPoint()).isEqualTo(8);
        assertThat(Symbol.of("9").getPoint()).isEqualTo(9);
        assertThat(Symbol.of("10").getPoint()).isEqualTo(10);
        assertThat(Symbol.of("J").getPoint()).isEqualTo(10);
        assertThat(Symbol.of("Q").getPoint()).isEqualTo(10);
        assertThat(Symbol.of("K").getPoint()).isEqualTo(10);
    }

    @Test
    @DisplayName("심볼의 Alias 확인")
    void getAlias() {
        assertThat(Symbol.of("A").getAlias()).isEqualTo("A");
        assertThat(Symbol.of("2").getAlias()).isEqualTo("2");
        assertThat(Symbol.of("3").getAlias()).isEqualTo("3");
        assertThat(Symbol.of("4").getAlias()).isEqualTo("4");
        assertThat(Symbol.of("5").getAlias()).isEqualTo("5");
        assertThat(Symbol.of("6").getAlias()).isEqualTo("6");
        assertThat(Symbol.of("7").getAlias()).isEqualTo("7");
        assertThat(Symbol.of("8").getAlias()).isEqualTo("8");
        assertThat(Symbol.of("9").getAlias()).isEqualTo("9");
        assertThat(Symbol.of("10").getAlias()).isEqualTo("10");
        assertThat(Symbol.of("J").getAlias()).isEqualTo("J");
        assertThat(Symbol.of("Q").getAlias()).isEqualTo("Q");
        assertThat(Symbol.of("K").getAlias()).isEqualTo("K");
    }
}
