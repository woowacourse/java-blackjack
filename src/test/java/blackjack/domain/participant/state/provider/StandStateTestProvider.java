package blackjack.domain.participant.state.provider;

import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_FIVE;
import static blackjack.Fixture.SPADE_KING;
import static blackjack.Fixture.SPADE_NINE;
import static blackjack.Fixture.SPADE_SIX;
import static blackjack.Fixture.SPADE_TEN;
import static blackjack.Fixture.SPADE_THREE;
import static blackjack.Fixture.SPADE_TWO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import blackjack.domain.result.MatchStatus;

public class StandStateTestProvider {

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
                        List.of(SPADE_TEN, SPADE_KING, SPADE_TWO)
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_NINE, SPADE_THREE)
                )
        );
    }

    public static Stream<Arguments> provideForJudgeMatchStatusWithStandStateTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_KING, SPADE_NINE, SPADE_TWO),
                        List.of(SPADE_KING, SPADE_NINE, SPADE_ACE),
                        MatchStatus.WIN
                ),
                Arguments.of(
                        List.of(SPADE_KING, SPADE_NINE, SPADE_TWO),
                        List.of(SPADE_KING, SPADE_NINE, SPADE_TWO),
                        MatchStatus.DRAW
                ),
                Arguments.of(
                        List.of(SPADE_KING, SPADE_NINE, SPADE_ACE),
                        List.of(SPADE_KING, SPADE_NINE, SPADE_TWO),
                        MatchStatus.LOSS
                )
        );
    }

    public static Stream<Arguments> provideForGetScoreTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_KING), 20
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_KING, SPADE_ACE), 21
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_FIVE, SPADE_SIX), 21
                )
        );
    }

    public static Stream<Arguments> provideForGetCardsTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_KING)
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_KING, SPADE_ACE)
                ),
                Arguments.of(
                        List.of(SPADE_TEN, SPADE_FIVE, SPADE_SIX)
                )
        );
    }

}
