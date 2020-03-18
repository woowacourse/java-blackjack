package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class SymbolTest {
    @Test
    @DisplayName("심볼 of")
    void of() {
        assertThat(Symbol.of("A") == Symbol.ACE).isTrue();
        assertThat(Symbol.of("2") == Symbol.TWO).isTrue();
        assertThat(Symbol.of("3") == Symbol.THREE).isTrue();
        assertThat(Symbol.of("4") == Symbol.FOUR).isTrue();
        assertThat(Symbol.of("5") == Symbol.FIVE).isTrue();
        assertThat(Symbol.of("6") == Symbol.SIX).isTrue();
        assertThat(Symbol.of("7") == Symbol.SEVEN).isTrue();
        assertThat(Symbol.of("8") == Symbol.EIGHT).isTrue();
        assertThat(Symbol.of("9") == Symbol.NINE).isTrue();
        assertThat(Symbol.of("10") == Symbol.TEN).isTrue();
        assertThat(Symbol.of("J") == Symbol.JACK).isTrue();
        assertThat(Symbol.of("Q") == Symbol.QUEEN).isTrue();
        assertThat(Symbol.of("K") == Symbol.KING).isTrue();
    }

    @Test
    @DisplayName("존재하지 않는 심볼")
    void of_NotExistSymbol_ThrowException() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Symbol.of("1"));
    }

    @ParameterizedTest
    @DisplayName("심볼이 Ace인지 확인")
    @CsvSource(value = {"10:False", "5:False", "A:True"}, delimiter = ':')
    void isAce(String input, Boolean expected) {
        assertThat(Symbol.of(input).isAce()).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("심볼의 점수 확인")
    @CsvSource(value = {"A:1", "2:2", "3:3", "4:4", "5:5", "6:6", "7:7", "8:8", "9:9", "10:10", "J:10", "Q:10", "K:10"}, delimiter = ':')
    void getPoint(String input, int expected) {
        assertThat(Symbol.of(input).getPoint()).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("심볼의 Alias 확인")
    @CsvSource(value = {"A:A", "2:2", "3:3", "4:4", "5:5", "6:6", "7:7", "8:8", "9:9", "10:10", "J:J", "Q:Q", "K:K"}, delimiter = ':')
    void getAlias(String input, String expected) {
        assertThat(Symbol.of(input).getAlias()).isEqualTo(expected);
    }
}
