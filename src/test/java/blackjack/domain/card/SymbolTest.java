package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SymbolTest {

    private static Stream<Arguments> getSymbolAndScore() {
        return Stream.of(Arguments.of(Symbol.KING, 10),
                Arguments.of(Symbol.JACK, 10),
                Arguments.of(Symbol.QUEEN, 10),
                Arguments.of(Symbol.SEVEN, 7));
    }

    @DisplayName("각각의 카드 심볼은 고유한 점수를 가지고 있다.")
    @ParameterizedTest(name = "{displayName}")
    @MethodSource("getSymbolAndScore")
    public void compareScore(Symbol symbol, int score) {
        int symbolScore = symbol.getScore();

        assertThat(symbolScore).isEqualTo(score);
    }

    @DisplayName("심볼이 에이스면 true를 반환한다.")
    @Test
    public void isAce_True() {
        boolean isAce = Symbol.ACE.isAce();

        assertThat(isAce).isTrue();
    }

    @DisplayName("심볼이 에이스가 아니면 false를 반환한다.")
    @Test
    public void isAce_False() {
        boolean isAce = Symbol.KING.isAce();

        assertThat(isAce).isFalse();
    }
}
