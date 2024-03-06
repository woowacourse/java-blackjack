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

    //TODO: 디스플레이네임 수정 필요, 좀더 명확하고, 이해하기 쉽게
    // 코드 중복이 발생한다.
    @DisplayName("에이스를 포함한 카드의 합이 21이하인 경우 에이스는 11로 계산한다.")
    @ParameterizedTest
    @MethodSource("sumAce11ParameterProvider")
    void sumAce11(final List<Card> cards, final int expected) {
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

    static Stream<Arguments> sumAce11ParameterProvider() {
        return Stream.of(
                Arguments.of(
                        List.of(new Card(CardNumber.ACE, CardShape.HEART),
                                new Card(CardNumber.EIGHT, CardShape.SPADE),
                                new Card(CardNumber.TWO, CardShape.CLOVER)),
                        21),
                Arguments.of(List.of(new Card(CardNumber.ACE, CardShape.DIAMOND),
                                new Card(CardNumber.TWO, CardShape.CLOVER),
                                new Card(CardNumber.FOUR, CardShape.CLOVER),
                                new Card(CardNumber.TWO, CardShape.CLOVER)),
                        19)
        );
    }
}
