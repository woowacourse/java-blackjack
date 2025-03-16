package blackjack.domain.card;

import static blackjack.fixture.TestFixture.provideBiggerAceCards;
import static blackjack.fixture.TestFixture.provideBiggerAndSmallerAceCards;
import static blackjack.fixture.TestFixture.provideSmallerAceCards;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class HandTest {

    @Test
    @DisplayName("하나의 카드로 hand를 생성한다")
    void createFromOneCard() {
        // Given
        final Card card = new Card(Shape.CLOB, CardScore.FIVE);

        // When
        Hand hand = new Hand(card);

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

    @Test
    @DisplayName("부분 카드를 반환한다")
    void subHand() {
        // Given
        Card firstCard = new Card(Shape.SPADE, CardScore.EIGHT);
        Card secondCard = new Card(Shape.HEART, CardScore.NINE);
        Hand hand = new Hand(List.of(firstCard, secondCard, new Card(Shape.HEART, CardScore.A)));

        // When & Then
        assertThat(hand.subHand(0, 2)).isEqualTo(new Hand(List.of(firstCard, secondCard)));
    }

    @ParameterizedTest
    @CsvSource({
            "-1,-1",
            "0,5"
    })
    @DisplayName("부분 카드 반환시 인덱스의 범위가 넘어간다면 예외가 발생한다")
    void subHandFailOutOfRange(final int startInclusive, final int endExclusive) {
        // Given
        Card firstCard = new Card(Shape.SPADE, CardScore.EIGHT);
        Card secondCard = new Card(Shape.HEART, CardScore.NINE);
        Hand hand = new Hand(List.of(firstCard, secondCard, new Card(Shape.HEART, CardScore.A)));

        // When & Then
        Assertions.assertThatThrownBy(() -> hand.subHand(startInclusive, endExclusive))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 인덱스는 0 이상 hand 크기 이하여야 합니다");
    }

    @Test
    @DisplayName("부분 카드 반환시 시작 인덱스가 끝 인덱스보다 크다면 예외가 발생한다")
    void subHandFailInvalidEndIndex() {
        // Given
        Card firstCard = new Card(Shape.SPADE, CardScore.EIGHT);
        Card secondCard = new Card(Shape.HEART, CardScore.NINE);
        Hand hand = new Hand(List.of(firstCard, secondCard, new Card(Shape.HEART, CardScore.A)));

        // When & Then
        Assertions.assertThatThrownBy(() -> hand.subHand(2, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 끝 인덱스는 시작 인덱스보다 커야합니다");
    }
}
