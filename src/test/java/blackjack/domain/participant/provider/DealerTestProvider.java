package blackjack.domain.participant.provider;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.result.MatchStatus;

public class DealerTestProvider {

    public static Stream<Arguments> provideForStartWithDrawCardTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.TWO, CardPattern.DIAMOND),
                                new Card(CardNumber.EIGHT, CardPattern.DIAMOND),
                                new Card(CardNumber.KING, CardPattern.DIAMOND)
                        ),
                        List.of(
                                new Card(CardNumber.TWO, CardPattern.DIAMOND),
                                new Card(CardNumber.EIGHT, CardPattern.DIAMOND),
                                new Card(CardNumber.KING, CardPattern.DIAMOND)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.NINE, CardPattern.SPADE),
                                new Card(CardNumber.KING, CardPattern.HEART),
                                new Card(CardNumber.SEVEN, CardPattern.HEART),
                                new Card(CardNumber.JACK, CardPattern.HEART)
                        ),
                        List.of(
                                new Card(CardNumber.NINE, CardPattern.SPADE),
                                new Card(CardNumber.KING, CardPattern.HEART)
                        )
                )
        );
    }

    public static Stream<Arguments> provideForPlayerLoseByBust() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.KING, CardPattern.DIAMOND),
                                new Card(CardNumber.SIX, CardPattern.DIAMOND),
                                new Card(CardNumber.JACK, CardPattern.DIAMOND),
                                new Card(CardNumber.EIGHT, CardPattern.SPADE)
                        ),
                        List.of(
                                new Card(CardNumber.TEN, CardPattern.SPADE)
                        ),
                        MatchStatus.LOSS
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.KING, CardPattern.DIAMOND),
                                new Card(CardNumber.SIX, CardPattern.DIAMOND),
                                new Card(CardNumber.JACK, CardPattern.DIAMOND),
                                new Card(CardNumber.EIGHT, CardPattern.SPADE)
                        ),
                        List.of(
                                new Card(CardNumber.ACE, CardPattern.SPADE)
                        ),
                        MatchStatus.WIN
                )
        );
    }

    public static Stream<Arguments> provideForDealerLoseByBust() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.KING, CardPattern.DIAMOND),
                                new Card(CardNumber.SIX, CardPattern.DIAMOND),
                                new Card(CardNumber.EIGHT, CardPattern.SPADE),
                                new Card(CardNumber.TEN, CardPattern.SPADE)
                        ),
                        List.of(
                                new Card(CardNumber.JACK, CardPattern.DIAMOND)
                        ),
                        MatchStatus.WIN
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.KING, CardPattern.DIAMOND),
                                new Card(CardNumber.SIX, CardPattern.DIAMOND),
                                new Card(CardNumber.EIGHT, CardPattern.SPADE),
                                new Card(CardNumber.TEN, CardPattern.SPADE)
                        ),
                        List.of(
                                new Card(CardNumber.FOUR, CardPattern.DIAMOND)
                        ),
                        MatchStatus.LOSS
                )
        );
    }

    public static Stream<Arguments> provideForDealerCalculateWinningResultTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.KING, CardPattern.DIAMOND),
                                new Card(CardNumber.JACK, CardPattern.DIAMOND),
                                new Card(CardNumber.TWO, CardPattern.DIAMOND),
                                new Card(CardNumber.THREE, CardPattern.DIAMOND)
                        ),
                        MatchStatus.LOSS
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.KING, CardPattern.DIAMOND),
                                new Card(CardNumber.JACK, CardPattern.DIAMOND),
                                new Card(CardNumber.QUEEN, CardPattern.DIAMOND),
                                new Card(CardNumber.TEN, CardPattern.DIAMOND)
                        ),
                        MatchStatus.LOSS
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.FOUR, CardPattern.DIAMOND),
                                new Card(CardNumber.THREE, CardPattern.DIAMOND),
                                new Card(CardNumber.KING, CardPattern.DIAMOND),
                                new Card(CardNumber.JACK, CardPattern.DIAMOND)
                        ),
                        MatchStatus.WIN
                )
        );
    }

}
