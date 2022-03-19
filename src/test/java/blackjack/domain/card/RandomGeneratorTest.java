package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Deque;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class RandomGeneratorTest {

    @ParameterizedTest
    @MethodSource("getCombinationOfCard")
    @DisplayName("덱에 카드가 포함되어 있는지 확인한다.")
    void checkContainsCard(Card card) {
        Deque<Card> deck = new RandomGenerator().generate();

        assertThat(deck.contains(card)).isTrue();
    }

    private static Stream<Card> getCombinationOfCard() {
        return Stream.of(
                new Card(Type.SPADE, Score.ACE),
                new Card(Type.SPADE, Score.THREE),
                new Card(Type.CLOVER, Score.FIVE),
                new Card(Type.CLOVER, Score.SEVEN),
                new Card(Type.DIAMOND, Score.TEN),
                new Card(Type.DIAMOND, Score.JACK),
                new Card(Type.HEART, Score.KING),
                new Card(Type.HEART, Score.QUEEN)
        );
    }
}
