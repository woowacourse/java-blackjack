package blackjack.domain.participant.provider;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
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

    public static Stream<Arguments> provideForJudgeWinnersTest() {
        return Stream.of(
                Arguments.of(
                        List.of("sun"),
                        List.of(
                                new Card(CardNumber.KING, CardPattern.DIAMOND),
                                new Card(CardNumber.TEN, CardPattern.DIAMOND),
                                new Card(CardNumber.EIGHT, CardPattern.DIAMOND),
                                new Card(CardNumber.NINE, CardPattern.DIAMOND)
                        ),
                        Map.of("sun", MatchStatus.LOSS)
                ),
                Arguments.of(
                        List.of("sun", "if"),
                        List.of(
                                new Card(CardNumber.NINE, CardPattern.SPADE),
                                new Card(CardNumber.EIGHT, CardPattern.HEART),
                                new Card(CardNumber.TEN, CardPattern.SPADE),
                                new Card(CardNumber.EIGHT, CardPattern.SPADE),
                                new Card(CardNumber.TEN, CardPattern.HEART),
                                new Card(CardNumber.TWO, CardPattern.SPADE)
                        ),
                        Map.of(
                                "sun", MatchStatus.WIN,
                                "if", MatchStatus.LOSS
                        )
                )
        );
    }
}
