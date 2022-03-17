package blackjack.domain.participant.provider;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;

public class DealerTestProvider {

    private static Stream<Arguments> provideForDealerCannotDrawCardAnymoreTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.TEN, CardPattern.SPADE),
                                new Card(CardNumber.SEVEN, CardPattern.SPADE),
                                new Card(CardNumber.ACE, CardPattern.SPADE)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.EIGHT, CardPattern.SPADE),
                                new Card(CardNumber.KING, CardPattern.SPADE),
                                new Card(CardNumber.ACE, CardPattern.HEART)
                        )
                )
        );
    }

    public static Stream<Arguments> provideForGetFirstCardTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.ACE, CardPattern.DIAMOND),
                                new Card(CardNumber.KING, CardPattern.SPADE)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.KING, CardPattern.SPADE),
                                new Card(CardNumber.ACE, CardPattern.DIAMOND)
                        )
                )
        );
    }

}
