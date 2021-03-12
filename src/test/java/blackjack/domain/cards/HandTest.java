package blackjack.domain.cards;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class HandTest {

    private static Stream<Arguments> provideHand() {
        return Stream.of(
            Arguments.of(new Hand(Arrays.asList(
                Card.valueOf(Shape.DIAMOND, CardValue.ACE),
                Card.valueOf(Shape.SPADE, CardValue.SIX)
            )), 17),
            Arguments.of(new Hand(Arrays.asList(
                Card.valueOf(Shape.DIAMOND, CardValue.TEN),
                Card.valueOf(Shape.SPADE, CardValue.ACE),
                Card.valueOf(Shape.HEART, CardValue.ACE)
            )), 12),
            Arguments.of(new Hand(Arrays.asList(
                Card.valueOf(Shape.DIAMOND, CardValue.TEN),
                Card.valueOf(Shape.SPADE, CardValue.TEN),
                Card.valueOf(Shape.HEART, CardValue.TWO)
            )), 22),
            Arguments.of(new Hand(Arrays.asList(
                Card.valueOf(Shape.DIAMOND, CardValue.TEN),
                Card.valueOf(Shape.SPADE, CardValue.TEN),
                Card.valueOf(Shape.HEART, CardValue.ACE)
            )), 21)
        );
    }

    @ParameterizedTest
    @DisplayName("패의 점수를 반환")
    @MethodSource("provideHand")
    void getScore(Hand input, int expected) {
        assertThat(input.getScore()).isEqualTo(expected);
    }

    @Test
    @DisplayName("버스트 확인 기능")
    void isBust() {
        Hand bustHand = new Hand(Arrays.asList(
            Card.valueOf(Shape.DIAMOND, CardValue.QUEEN),
            Card.valueOf(Shape.SPADE, CardValue.KING),
            Card.valueOf(Shape.SPADE, CardValue.TWO)));
        assertThat(bustHand.isBust()).isTrue();

        Hand validHand = new Hand(Arrays.asList(
            Card.valueOf(Shape.DIAMOND, CardValue.QUEEN),
            Card.valueOf(Shape.SPADE, CardValue.KING),
            Card.valueOf(Shape.SPADE, CardValue.ACE)));
        assertThat(validHand.isBust()).isFalse();
    }

    @Test
    @DisplayName("블랙잭 확인 기능")
    void isBlackJack() {
        Hand bustHand = new Hand(Arrays.asList(
            Card.valueOf(Shape.DIAMOND, CardValue.ACE),
            Card.valueOf(Shape.SPADE, CardValue.KING)));
        assertThat(bustHand.isBlackJack()).isTrue();

        Hand validHand = new Hand(Arrays.asList(
            Card.valueOf(Shape.DIAMOND, CardValue.FIVE),
            Card.valueOf(Shape.SPADE, CardValue.NINE),
            Card.valueOf(Shape.SPADE, CardValue.TWO)));
        assertThat(validHand.isBlackJack()).isFalse();
    }
}
