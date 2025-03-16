package blackjack.domain.card;

import static blackjack.fixture.TestFixture.provideBiggerAceCards;
import static blackjack.fixture.TestFixture.provideBiggerAndSmallerAceCards;
import static blackjack.fixture.TestFixture.provideSmallerAceCards;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HandTest {

    @Test
    @DisplayName("하나의 카드로 hand를 생성한다")
    void createFromOneCard() {
        // Given
        final Card card = new Card(Shape.CLOB, CardScore.FIVE);

        // When
        Hand hand = Hand.from(card);

        // Then
        assertThat(hand.getHand()).isEqualTo(List.of(card));
    }

    @DisplayName("21 이하에서 카드 최대 합을 구한다")
    @ParameterizedTest
    @MethodSource
    void calculateMaxScore(final Hand hand, final int expected) {
        // given

        // when & then
        assertThat(hand.calculateResult()).isEqualTo(expected);
    }

    private static Stream<Arguments> calculateMaxScore() {
        return Stream.of(
                Arguments.of(provideSmallerAceCards(), 18),
                Arguments.of(provideBiggerAceCards(), 21),
                Arguments.of(provideBiggerAndSmallerAceCards(), 17)
        );
    }

    @DisplayName("21 이하에서 카드 최소 합을 구한다")
    @ParameterizedTest
    @MethodSource
    void calculateWithHardHand(final Hand hand, final int expected) {
        // given

        // when & then
        assertThat(hand.calculateWithHardHand()).isEqualTo(expected);
    }

    private static Stream<Arguments> calculateWithHardHand() {
        return Stream.of(
                Arguments.of(provideSmallerAceCards(), 18),
                Arguments.of(provideBiggerAceCards(), 11),
                Arguments.of(provideBiggerAndSmallerAceCards(), 7)
        );
    }
}
