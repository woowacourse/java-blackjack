package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class DealerTest {

    @ParameterizedTest
    @MethodSource("generateCardsAndFlag")
    @DisplayName("카드를 더 받을 수 있는 여부를 반환한다")
    void getParticipantIntention(List<Card> cards, boolean expectedFlag) {
        Dealer dealer = new Dealer();
        for (Card card : cards) {
            dealer.addCard(card);
        }

        assertThat(dealer.canReceive()).isEqualTo(expectedFlag);
    }

    static Stream<Arguments> generateCardsAndFlag() {
        return Stream.of(
                Arguments.of(List.of(new Card(Symbol.SPADE, Number.SEVEN), new Card(Symbol.CLOVER, Number.TWO)), true),
                Arguments.of(List.of(new Card(Symbol.HEART, Number.NINE), new Card(Symbol.HEART, Number.TWO)), true),
                Arguments.of(List.of(new Card(Symbol.DIAMOND, Number.TEN), new Card(Symbol.SPADE, Number.TEN)), true),
                Arguments.of(List.of(new Card(Symbol.CLOVER, Number.THREE), new Card(Symbol.CLOVER, Number.TWO), new Card(Symbol.SPADE, Number.TWO), new Card(Symbol.HEART, Number.TWO)), false),
                Arguments.of(List.of(new Card(Symbol.CLOVER, Number.SEVEN), new Card(Symbol.SPADE, Number.SEVEN), new Card(Symbol.DIAMOND, Number.SEVEN)), false),
                Arguments.of(List.of(new Card(Symbol.CLOVER, Number.TEN), new Card(Symbol.SPADE, Number.TEN), new Card(Symbol.DIAMOND, Number.SEVEN)), false)
        );
    }
}
