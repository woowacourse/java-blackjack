package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class SymbolTest {
    @ParameterizedTest
    @DisplayName("심볼 of")
    @ValueSource(strings = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"})
    void of(String symbol) {
        assertThat(Symbol.of(symbol)).isInstanceOf(Symbol.class);
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
