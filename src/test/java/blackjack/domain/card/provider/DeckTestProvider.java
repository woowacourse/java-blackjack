package blackjack.domain.card.provider;

import static blackjack.Fixture.DIAMOND_ACE;
import static blackjack.Fixture.HEART_ACE;
import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_EIGHT;
import static blackjack.Fixture.SPADE_QUEEN;
import static blackjack.Fixture.SPADE_TWO;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

public class DeckTestProvider {

    public static Stream<Arguments> provideForCardDuplicatedExceptionTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_ACE, SPADE_ACE)
                ),
                Arguments.of(
                        List.of(SPADE_ACE, SPADE_QUEEN, SPADE_ACE)
                )
        );
    }

    public static Stream<Arguments> provideForDistributeInitialCardsTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_ACE, SPADE_EIGHT)
                ),
                Arguments.of(
                        List.of(SPADE_ACE, SPADE_EIGHT, SPADE_TWO)
                )
        );
    }

    public static Stream<Arguments> provideForDrawCardTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_ACE)
                ),
                Arguments.of(
                        List.of(SPADE_ACE, SPADE_EIGHT)
                ),
                Arguments.of(
                        List.of(SPADE_ACE, SPADE_EIGHT, SPADE_TWO)
                )
        );
    }

    public static Stream<Arguments> provideForDrawableCardNotExistExceptionTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_ACE)
                ),
                Arguments.of(
                        List.of(SPADE_ACE, DIAMOND_ACE, HEART_ACE)
                )
        );
    }
}
