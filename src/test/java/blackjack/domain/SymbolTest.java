package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
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

    @ParameterizedTest
    @MethodSource("getSymbolAndScore")
    public void compareScore(Symbol symbol, int score) {
        assertThat(symbol.getScore()).isEqualTo(score);
    }
}
