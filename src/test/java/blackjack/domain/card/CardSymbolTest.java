package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CardSymbolTest {

    private static Stream<Arguments> symbolTest() {
        return Stream.of(
            Arguments.of("클로버", CardSymbol.CLOVER),
            Arguments.of("다이아몬드", CardSymbol.DIAMOND)
        );
    }

    @ParameterizedTest
    @DisplayName("심볼 동일 확인 테스트")
    @MethodSource("symbolTest")
    void symbol(String symbol, CardSymbol expected) {
        CardSymbol cardSymbol = CardSymbol.matchByInput(symbol);
        assertThat(cardSymbol).isEqualTo(expected);
    }
}
