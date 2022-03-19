package blackjack.domain.participant.provider;

import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_EIGHT;
import static blackjack.Fixture.SPADE_FIVE;
import static blackjack.Fixture.SPADE_FOUR;
import static blackjack.Fixture.SPADE_KING;
import static blackjack.Fixture.SPADE_SEVEN;
import static blackjack.Fixture.SPADE_SIX;
import static blackjack.Fixture.SPADE_TEN;
import static blackjack.Fixture.SPADE_THREE;
import static blackjack.Fixture.SPADE_TWO;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

public class DealerTestProvider {

    public static Stream<Arguments> provideForReaderToPlayTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_ACE, SPADE_TEN)
                ),
                Arguments.of(
                        List.of(SPADE_ACE, SPADE_TWO)
                )
        );
    }

    public static Stream<Arguments> provideForChangeToStandWhenInitializedTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_SEVEN)
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_EIGHT)
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_KING)
                )
        );
    }

    public static Stream<Arguments> provideForDrawCardTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_FIVE), SPADE_ACE
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_FOUR), SPADE_TWO
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_FOUR), SPADE_ACE
                )
        );
    }

    public static Stream<Arguments> provideForChangeToStandWhenDrawCardTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_SIX), SPADE_ACE
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_FIVE), SPADE_TWO
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_FIVE), SPADE_THREE
                )
        );
    }

    public static Stream<Arguments> provideForKeepHitStateWhenDrawCardTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_FIVE), SPADE_ACE
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_FOUR), SPADE_TWO
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_FOUR), SPADE_ACE
                )
        );
    }

}
