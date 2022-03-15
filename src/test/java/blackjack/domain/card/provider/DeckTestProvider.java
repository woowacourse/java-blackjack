package blackjack.domain.card.provider;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;

public class DeckTestProvider {

    public static Stream<Arguments> provideForCardDuplicatedExceptionTest() {
        final Card duplicatedCard = new Card(CardNumber.ACE, CardPattern.SPADE);
        final Card notDuplicatedCard = new Card(CardNumber.KING, CardPattern.DIAMOND);

        return Stream.of(
                Arguments.of(
                        List.of(
                                duplicatedCard,
                                duplicatedCard
                        )
                ),
                Arguments.of(
                        List.of(
                                duplicatedCard,
                                notDuplicatedCard,
                                duplicatedCard
                        )
                )
        );
    }

    public static Stream<Arguments> provideForDrawCardTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.ACE, CardPattern.DIAMOND),
                                new Card(CardNumber.EIGHT, CardPattern.DIAMOND)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.ACE, CardPattern.SPADE),
                                new Card(CardNumber.EIGHT, CardPattern.HEART),
                                new Card(CardNumber.EIGHT, CardPattern.DIAMOND)
                        )
                )
        );
    }

    public static Stream<Arguments> provideForDrawableCardNotExistExceptionTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.ACE, CardPattern.SPADE)
                        ),
                        List.of(
                                new Card(CardNumber.ACE, CardPattern.SPADE),
                                new Card(CardNumber.ACE, CardPattern.DIAMOND),
                                new Card(CardNumber.ACE, CardPattern.HEART)
                        )
                )
        );
    }
}
