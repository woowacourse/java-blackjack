package blackjack.domain.participant.provider;

import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_EIGHT;
import static blackjack.Fixture.SPADE_NINE;
import static blackjack.Fixture.SPADE_TEN;
import static blackjack.Fixture.SPADE_TWO;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

public class PlayerTestProvider {

    public static Stream<Arguments> provideForReadyToPlayTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_ACE, SPADE_TEN)
                ),
                Arguments.of(
                        List.of(SPADE_ACE, SPADE_TWO)
                )
        );
    }

    public static Stream<Arguments> provideForDrawCardTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_NINE), SPADE_ACE
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_EIGHT), SPADE_ACE
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_TWO), SPADE_ACE
                )
        );
    }
}
