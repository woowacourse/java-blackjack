package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FormatterTest {

    private static Stream<Arguments> testCasesForFormatCard() {
        return Stream.of(
                Arguments.of(new Card(CardSuit.CLUB, CardRank.ACE), "A클로버"),
                Arguments.of(new Card(CardSuit.DIAMOND, CardRank.TWO), "2다이아몬드"),
                Arguments.of(new Card(CardSuit.HEART, CardRank.JACK), "J하트"),
                Arguments.of(new Card(CardSuit.SPADE, CardRank.QUEEN), "Q스페이드")
        );
    }

    @ParameterizedTest
    @MethodSource("testCasesForFormatCard")
    void 카드의_이름을_포맷한다(Card card, String expected) {
        // when & then
        assertEquals(expected, Formatter.formatCard(card));
    }
}
