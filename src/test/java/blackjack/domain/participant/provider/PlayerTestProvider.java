package blackjack.domain.participant.provider;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;

public class PlayerTestProvider {

    public static Stream<Arguments> provideForCheckBlackjackStateTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.ACE, CardPattern.DIAMOND),
                                new Card(CardNumber.KING, CardPattern.SPADE)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.ACE, CardPattern.DIAMOND),
                                new Card(CardNumber.QUEEN, CardPattern.SPADE)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.ACE, CardPattern.DIAMOND),
                                new Card(CardNumber.TEN, CardPattern.SPADE)
                        )
                )
        );
    }

    public static Stream<Arguments> provideForCheckBustStateTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.TWO, CardPattern.SPADE),
                                new Card(CardNumber.KING, CardPattern.DIAMOND),
                                new Card(CardNumber.KING, CardPattern.SPADE)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.KING, CardPattern.HEART),
                                new Card(CardNumber.KING, CardPattern.DIAMOND),
                                new Card(CardNumber.KING, CardPattern.SPADE)
                        )
                )

        );
    }

    public static Stream<Arguments> provideForCheckStandWithInNotBlackTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.KING, CardPattern.SPADE),
                                new Card(CardNumber.KING, CardPattern.DIAMOND),
                                new Card(CardNumber.ACE, CardPattern.SPADE)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.KING, CardPattern.HEART),
                                new Card(CardNumber.SIX, CardPattern.DIAMOND),
                                new Card(CardNumber.FIVE, CardPattern.SPADE)
                        )
                )
        );
    }

    public static Stream<Arguments> provideForCheckHitStateTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.KING, CardPattern.SPADE),
                                new Card(CardNumber.TWO, CardPattern.DIAMOND),
                                new Card(CardNumber.TWO, CardPattern.SPADE)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.KING, CardPattern.HEART),
                                new Card(CardNumber.FIVE, CardPattern.DIAMOND),
                                new Card(CardNumber.FIVE, CardPattern.SPADE)
                        )
                )
        );
    }

}
