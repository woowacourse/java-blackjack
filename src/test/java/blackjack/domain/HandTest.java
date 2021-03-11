package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Shape;
import blackjack.exception.InvalidNameInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

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

    @DisplayName("버스트 확인 기능")
    @Test
    void isBust() throws InvalidNameInputException {
        Hand bustHand = TestSetUp.createBustPlayer().getHand();
        assertThat(bustHand.isBust()).isTrue();

        Hand validHand = TestSetUp.createBlackJackPlayer().getHand();
        assertThat(validHand.isBust()).isFalse();
    }
}
