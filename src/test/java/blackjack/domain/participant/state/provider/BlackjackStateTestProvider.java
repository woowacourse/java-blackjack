package blackjack.domain.participant.state.provider;

import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_KING;
import static blackjack.Fixture.SPADE_NINE;
import static blackjack.Fixture.SPADE_TEN;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

public class BlackjackStateTestProvider {

    public static Stream<Arguments> provideForCardSizeExceptionTest() {
        return Stream.of(
                Arguments.of(
                        Collections.emptyList()
                ),
                Arguments.of(
                        List.of(SPADE_TEN)
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_NINE, SPADE_ACE)
                )
        );
    }

    public static Stream<Arguments> provideForCardScoreExceptionTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_KING)
                ),
                Arguments.of(
                        List.of(SPADE_ACE, SPADE_NINE)
                )
        );
    }

    public static Stream<Arguments> provideForGetScoreTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_ACE, SPADE_TEN), 21
                ),
                Arguments.of(
                        List.of(SPADE_ACE, SPADE_KING), 21
                )
        );
    }

    public static Stream<Arguments> provideForGetCardsTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_ACE, SPADE_TEN)
                ),
                Arguments.of(
                        List.of(SPADE_ACE, SPADE_KING)
                )
        );
    }

}
