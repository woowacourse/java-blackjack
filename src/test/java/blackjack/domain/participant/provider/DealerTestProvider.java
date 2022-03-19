package blackjack.domain.participant.provider;

import static blackjack.Fixture.DIAMOND_ACE;
import static blackjack.Fixture.HEART_ACE;
import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_EIGHT;
import static blackjack.Fixture.SPADE_KING;
import static blackjack.Fixture.SPADE_SEVEN;
import static blackjack.Fixture.SPADE_TEN;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

public class DealerTestProvider {

    private static Stream<Arguments> provideForDealerCannotDrawCardAnymoreTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                SPADE_TEN,
                                SPADE_SEVEN,
                                SPADE_ACE
                        )
                ),
                Arguments.of(
                        List.of(
                                SPADE_EIGHT,
                                SPADE_KING,
                                HEART_ACE
                        )
                )
        );
    }

    public static Stream<Arguments> provideForGetFirstCardTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                DIAMOND_ACE,
                                SPADE_KING
                        )
                ),
                Arguments.of(
                        List.of(
                                SPADE_KING,
                                DIAMOND_ACE
                        )
                )
        );
    }

}
