package blackjack.domain.participant.provider;

import static blackjack.Fixture.DIAMOND_ACE;
import static blackjack.Fixture.DIAMOND_FIVE;
import static blackjack.Fixture.DIAMOND_KING;
import static blackjack.Fixture.DIAMOND_SIX;
import static blackjack.Fixture.DIAMOND_TWO;
import static blackjack.Fixture.HEART_KING;
import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_FIVE;
import static blackjack.Fixture.SPADE_KING;
import static blackjack.Fixture.SPADE_QUEEN;
import static blackjack.Fixture.SPADE_TEN;
import static blackjack.Fixture.SPADE_TWO;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

public class PlayerTestProvider {

    public static Stream<Arguments> provideForCheckBlackjackStateTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                DIAMOND_ACE,
                                SPADE_KING
                        )
                ),
                Arguments.of(
                        List.of(
                                DIAMOND_ACE,
                                SPADE_QUEEN
                        )
                ),
                Arguments.of(
                        List.of(
                                DIAMOND_ACE,
                                SPADE_TEN
                        )
                )
        );
    }

    public static Stream<Arguments> provideForCheckBustStateTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                SPADE_TWO,
                                DIAMOND_KING,
                                SPADE_KING
                        )
                ),
                Arguments.of(
                        List.of(
                                HEART_KING,
                                DIAMOND_KING,
                                SPADE_KING
                        )
                )

        );
    }

    public static Stream<Arguments> provideForCheckStandWithInNotBlackTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                SPADE_KING,
                                DIAMOND_KING,
                                SPADE_ACE
                        )
                ),
                Arguments.of(
                        List.of(
                                HEART_KING,
                                DIAMOND_SIX,
                                SPADE_FIVE
                        )
                )
        );
    }

    public static Stream<Arguments> provideForCheckHitStateTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                SPADE_KING,
                                DIAMOND_TWO,
                                SPADE_TWO
                        )
                ),
                Arguments.of(
                        List.of(
                                HEART_KING,
                                DIAMOND_FIVE,
                                SPADE_FIVE
                        )
                )
        );
    }

}
