package blackjack.domain.participant.state.provider;

import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_KING;
import static blackjack.Fixture.SPADE_NINE;
import static blackjack.Fixture.SPADE_TEN;
import static blackjack.Fixture.SPADE_THREE;
import static blackjack.Fixture.SPADE_TWO;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

public class InitialStateTestProvider {

    public static Stream<Arguments> provideForCardSizeExceptionTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_TEN)
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_TWO, SPADE_THREE)
                )
        );
    }

    public static Stream<Arguments> provideForIsBlackjackStateTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_ACE, SPADE_TEN)
                ),
                Arguments.of(
                        List.of(SPADE_ACE, SPADE_KING)
                )
        );
    }

    public static Stream<Arguments> provideForIsHitStateTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_KING)
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_NINE)
                )
        );
    }

}
