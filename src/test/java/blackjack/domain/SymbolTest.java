package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SymbolTest {

    private static Stream<Arguments> getSymbolAndScore() {
        return Stream.of(Arguments.of(Symbol.KING, 10),
            Arguments.of(Symbol.JACK, 10),
            Arguments.of(Symbol.QUEEN, 10),
            Arguments.of(Symbol.SEVEN, 7));
    }

    @ParameterizedTest(name = "{displayName}")
    @DisplayName("각각의 카드 심볼은 고유한 점수를 가지고 있다.")
    @MethodSource("getSymbolAndScore")
    public void compareScore(Symbol symbol, int score) {
        assertThat(symbol.getScore()).isEqualTo(score);
    }
}
