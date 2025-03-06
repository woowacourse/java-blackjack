package blackjack.domain.card;

import static blackjack.fixture.TestFixture.provideBiggerAceCards;
import static blackjack.fixture.TestFixture.provideBiggerAndSmallerAceCards;
import static blackjack.fixture.TestFixture.provideSmallerAceCards;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardsTest {

    @DisplayName("21 이하에서 카드 최대 합을 구한다")
    @ParameterizedTest
    @MethodSource
    void calculateMaxSum(final Cards cards, final int expected) {
        // given

        // when & then
        assertThat(cards.calculateResult()).isEqualTo(expected);
    }

    private static Stream<Arguments> calculateMaxSum() {
        return Stream.of(
                Arguments.of(provideSmallerAceCards(), 18),
                Arguments.of(provideBiggerAceCards(), 21),
                Arguments.of(provideBiggerAndSmallerAceCards(), 17)
        );
    }

    @DisplayName("21 이하에서 카드 최소 합을 구한다")
    @ParameterizedTest
    @MethodSource
    void calculateMinSum(final Cards cards, final int expected) {
        // given

        // when & then
        assertThat(cards.calculateMinSum()).isEqualTo(expected);
    }

    private static Stream<Arguments> calculateMinSum() {
        return Stream.of(
                Arguments.of(provideSmallerAceCards(), 18),
                Arguments.of(provideBiggerAceCards(), 11),
                Arguments.of(provideBiggerAndSmallerAceCards(), 7)
        );
    }
}