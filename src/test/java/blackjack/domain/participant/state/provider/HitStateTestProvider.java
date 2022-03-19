package blackjack.domain.participant.state.provider;

import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_EIGHT;
import static blackjack.Fixture.SPADE_FIVE;
import static blackjack.Fixture.SPADE_FOUR;
import static blackjack.Fixture.SPADE_KING;
import static blackjack.Fixture.SPADE_NINE;
import static blackjack.Fixture.SPADE_TEN;
import static blackjack.Fixture.SPADE_THREE;
import static blackjack.Fixture.SPADE_TWO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

public class HitStateTestProvider {

    public static Stream<Arguments> provideForCardSizeExceptionTest() {
        return Stream.of(
                Arguments.of(
                        Collections.emptyList()
                ),
                Arguments.of(
                        List.of(SPADE_TEN)
                )
        );
    }

    public static Stream<Arguments> provideForCardScoreExceptionTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_ACE, SPADE_TEN)
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_KING, SPADE_ACE)
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_KING, SPADE_TWO)
                )
        );
    }

    public static Stream<Arguments> provideForKeepHitStateWhenDrawCardTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_NINE), SPADE_ACE
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_EIGHT), SPADE_TWO
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_EIGHT), SPADE_ACE
                )
        );
    }

    public static Stream<Arguments> provideForChangeToStandStateWhenDrawCardTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_KING), SPADE_ACE
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_NINE), SPADE_TWO
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_EIGHT), SPADE_THREE
                )
        );
    }

    public static Stream<Arguments> provideForChangeToBustStateWhenDrawCardTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_KING), SPADE_TWO
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_NINE), SPADE_THREE
                )
        );
    }

    public static Stream<Arguments> provideForGetScoreTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_KING), 20
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_NINE), 19
                )
        );
    }

    public static Stream<Arguments> provideForGetCardsTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_KING)
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_NINE)
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_FIVE, SPADE_FOUR)
                )
        );
    }

}
