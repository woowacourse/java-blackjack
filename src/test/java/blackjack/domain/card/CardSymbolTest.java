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
            Arguments.of("클로버", CardSymbol.from("클로버")),
            Arguments.of("다이아몬드", CardSymbol.from("다이아몬드"))
        );
    }

    @ParameterizedTest
    @DisplayName("심볼 동일 인스턴스 확인 테스")
    @MethodSource("symbolTest")
    void symbol(String symbol, CardSymbol value) {

        CardSymbol cardSymbol = CardSymbol.from(symbol);

        assertThat(cardSymbol).isEqualTo(value);
    }
}
