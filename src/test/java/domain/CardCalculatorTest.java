package domain;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardCalculatorTest {
    @DisplayName("카드의 합을 구한다.")
    @ParameterizedTest
    @MethodSource("sumParameterProvider")
    void sum(final List<Card> cards, final int expected) {
        // given
        final CardCalculator cardCalculator = new CardCalculator();

        // when
        final int result = cardCalculator.sum(cards);

        // then
        Assertions.assertThat(result).isEqualTo(expected);
    }


    static Stream<Arguments> sumParameterProvider() {
        return Stream.of(
                Arguments.of(
                        List.of(new Card(CardNumber.TWO, CardShape.HEART),
                                new Card(CardNumber.EIGHT, CardShape.SPADE),
                                new Card(CardNumber.JACK, CardShape.CLOVER)),
                        20
                ),
                Arguments.of(
                        List.of(new Card(CardNumber.THREE, CardShape.DIAMOND),
                                new Card(CardNumber.NINE, CardShape.CLOVER),
                                new Card(CardNumber.NINE, CardShape.CLOVER)),
                        21
                )
        );
    }
}
