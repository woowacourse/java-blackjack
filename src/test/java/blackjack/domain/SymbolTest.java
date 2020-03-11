package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class SymbolTest {
    @ParameterizedTest
    @DisplayName("스코어를 잘 불러오는지 확인")
    @CsvSource(value = {"ACE,1", "TEN,10", "KING,10"})
    void checkScore(Symbol value, int expected) {
        assertThat(value.getScore()).isEqualTo(expected);
    }
}
