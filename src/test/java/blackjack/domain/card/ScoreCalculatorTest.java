package blackjack.domain.card;

import static blackjack.Fixture.CLOVER_ACE;
import static blackjack.Fixture.DIAMOND_ACE;
import static blackjack.Fixture.HEART_ACE;
import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_FIVE;
import static blackjack.Fixture.SPADE_KING;
import static blackjack.Fixture.SPADE_TEN;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ScoreCalculatorTest {

    @DisplayName("합계를 계산할 수 있어야 한다.")
    @ParameterizedTest
    @MethodSource("provideForCalculateScoreTest")
    void calculateScoreTest(final List<Card> cards, final int expectedScore) {

    }

    public static Stream<Arguments> provideForCalculateScoreTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_FIVE, SPADE_TEN),
                        15
                ),
                Arguments.of(
                        List.of(SPADE_ACE, SPADE_TEN),
                        21
                ),
                Arguments.of(
                        List.of(SPADE_ACE, SPADE_TEN, SPADE_FIVE),
                        16
                ),
                Arguments.of(
                        List.of(SPADE_ACE, SPADE_TEN, SPADE_KING),
                        21
                ),
                Arguments.of(
                        List.of(SPADE_ACE, HEART_ACE, SPADE_FIVE),
                        17
                ),
                Arguments.of(
                        List.of(SPADE_ACE, HEART_ACE, DIAMOND_ACE, CLOVER_ACE),
                        14
                )
        );
    }

}