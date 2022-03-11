package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
class SymbolTest {
    @ParameterizedTest
    @CsvSource(value = {
            "SPADE,스페이드",
            "HEART,하트",
            "DIAMOND,다이아몬드",
            "CLOVER,클로버"
    })
    @DisplayName("Symbol 생성 테스트")
    void createSymbolTest(Symbol expectedSymbol, String inputSymbolName) {
        Symbol symbol = Symbol.of(inputSymbolName);
        assertThat(symbol)
                .isEqualTo(expectedSymbol);
    }
}
