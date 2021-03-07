package blackjack.domain.card;

import blackjack.domain.card.painting.Suit;
import blackjack.domain.card.painting.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {

    @DisplayName("카드들의 점수의 합을 구한다")
    @ParameterizedTest
    @MethodSource
    void testCalculateScore(List<Card> cards, int expectedScore) {
        //given
        Cards testCards = new Cards(cards);
        //when
        int score = testCards.calculateScore();
        //then
        assertThat(score).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> testCalculateScore() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        new Card(Suit.CLOVER, Symbol.JACK), new Card(Suit.DIAMOND, Symbol.FOUR),
                        new Card(Suit.HEART, Symbol.ACE)), 15),
                Arguments.of(Arrays.asList(
                        new Card(Suit.CLOVER, Symbol.JACK), new Card(Suit.DIAMOND, Symbol.TEN),
                        new Card(Suit.HEART, Symbol.ACE)), 21),
                Arguments.of(Arrays.asList(
                        new Card(Suit.CLOVER, Symbol.TWO), new Card(Suit.DIAMOND, Symbol.SEVEN),
                        new Card(Suit.HEART, Symbol.ACE)), 20),
                Arguments.of(Arrays.asList(
                        new Card(Suit.CLOVER, Symbol.KING), new Card(Suit.DIAMOND, Symbol.SIX),
                        new Card(Suit.HEART, Symbol.FOUR)), 20),
                Arguments.of(Arrays.asList(
                        new Card(Suit.CLOVER, Symbol.NINE), new Card(Suit.DIAMOND, Symbol.QUEEN),
                        new Card(Suit.HEART, Symbol.FIVE)), 24),
                Arguments.of(Arrays.asList(
                        new Card(Suit.CLOVER, Symbol.NINE), new Card(Suit.HEART, Symbol.TWO),
                        new Card(Suit.DIAMOND, Symbol.QUEEN), new Card(Suit.HEART, Symbol.ACE)), 22),
                Arguments.of(Arrays.asList(
                        new Card(Suit.CLOVER, Symbol.FIVE), new Card(Suit.HEART, Symbol.ACE),
                        new Card(Suit.DIAMOND, Symbol.TEN)), 16),
                Arguments.of(Arrays.asList(
                        new Card(Suit.CLOVER, Symbol.ACE), new Card(Suit.HEART, Symbol.ACE),
                        new Card(Suit.DIAMOND, Symbol.ACE), new Card(Suit.SPADE, Symbol.ACE)), 14)
        );
    }
}
