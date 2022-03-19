package blackjack.domain.participant.provider;

import static blackjack.Fixture.SPADE_EIGHT;
import static blackjack.Fixture.SPADE_KING;
import static blackjack.Fixture.SPADE_NINE;
import static blackjack.Fixture.SPADE_TEN;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

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
                                SPADE_KING, SPADE_TEN
                        ), "sun"
                ),
                Arguments.of(
                        List.of(
                                SPADE_EIGHT, SPADE_NINE
                        ), "if"
                )
        );
    }

}
