package blackjack.domain.card.provider;

import static blackjack.Fixture.DIAMOND_ACE;
import static blackjack.Fixture.DIAMOND_EIGHT;
import static blackjack.Fixture.HEART_ACE;
import static blackjack.Fixture.HEART_EIGHT;
import static blackjack.Fixture.HEART_KING;
import static blackjack.Fixture.SPADE_ACE;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

public class DeckTestProvider {

    public static Stream<Arguments> provideForCardDuplicatedExceptionTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                SPADE_ACE,
                                SPADE_ACE
                        )
                ),
                Arguments.of(
                        List.of(
                                SPADE_ACE,
                                HEART_KING,
                                SPADE_ACE
                        )
                )
        );
    }

    public static Stream<Arguments> provideForDrawCardTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                DIAMOND_ACE,
                                DIAMOND_EIGHT
                        )
                ),
                Arguments.of(
                        List.of(
                                SPADE_ACE,
                                HEART_EIGHT,
                                DIAMOND_EIGHT
                        )
                )
        );
    }

    public static Stream<Arguments> provideForDrawableCardNotExistExceptionTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                SPADE_ACE
                        ),
                        List.of(
                                SPADE_ACE,
                                DIAMOND_ACE,
                                HEART_ACE
                        )
                )
        );
    }
}
