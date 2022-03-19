package blackjack.domain.participant.provider;

import static blackjack.Fixture.DIAMOND_EIGHT;
import static blackjack.Fixture.DIAMOND_KING;
import static blackjack.Fixture.DIAMOND_NINE;
import static blackjack.Fixture.DIAMOND_TEN;
import static blackjack.Fixture.HEART_EIGHT;
import static blackjack.Fixture.HEART_TEN;
import static blackjack.Fixture.SPADE_EIGHT;
import static blackjack.Fixture.SPADE_NINE;
import static blackjack.Fixture.SPADE_TEN;
import static blackjack.Fixture.SPADE_TWO;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import blackjack.domain.result.MatchStatus;

public class PlayersTestProvider {

    public static Stream<Arguments> provideForPlayerNameDuplicatedExceptionTest() {
        return Stream.of(
                Arguments.of(List.of("pobi", "pobi")),
                Arguments.of(List.of("pobi", "sun", "pobi"))
        );
    }

    public static Stream<Arguments> provideForPlayerCountTooManyExceptionTest() {
        return Stream.of(
                Arguments.of(List.of("1", "2", "3", "4", "5", "6", "7", "8", "9")),
                Arguments.of(List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j"))
        );
    }

    public static Stream<Arguments> provideForPlayerNameNotExistExceptionTest() {
        return Stream.of(
                Arguments.of(List.of("poby", "if", "sun"), "hello"),
                Arguments.of(List.of("hihi", "hibi"), "hi")
        );
    }

    public static Stream<Arguments> provideForGetPlayerCardsTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                DIAMOND_KING, DIAMOND_TEN
                        ), "sun"
                ),
                Arguments.of(
                        List.of(
                                DIAMOND_EIGHT, DIAMOND_NINE
                        ), "if"
                )
        );
    }

    public static Stream<Arguments> provideForJudgeWinnersTest() {
        return Stream.of(
                Arguments.of(
                        List.of("sun"),
                        List.of(
                                DIAMOND_KING, DIAMOND_TEN,
                                DIAMOND_EIGHT, DIAMOND_NINE
                        ),
                        Map.of("sun", MatchStatus.LOSS)
                ),
                Arguments.of(
                        List.of("sun", "if"),
                        List.of(
                                SPADE_NINE, HEART_EIGHT,
                                SPADE_TEN, SPADE_EIGHT,
                                HEART_TEN,
                                SPADE_TWO
                        ),
                        Map.of(
                                "sun", MatchStatus.WIN,
                                "if", MatchStatus.LOSS
                        )
                )
        );
    }
}
