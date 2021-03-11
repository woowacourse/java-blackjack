package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {
    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand(Arrays.asList(
                new Card(Suit.CLOVER, Value.JACK), new Card(Suit.DIAMOND, Value.FOUR),
                new Card(Suit.HEART, Value.ACE)));
    }

    @DisplayName("손에 들고 있는 카드 목록을 구한다")
    @Test
    void testGetCards() {
        //given
        //when
        List<Card> cards = hand.getCards();
        //then
        assertThat(cards.size()).isEqualTo(3);
    }

    @DisplayName("손에 들고 있는 카드들의 점수의 합을 구한다")
    @ParameterizedTest
    @MethodSource
    void testCalculateScore(List<Card> cards, int expectedScore) {
        //given
        Hand hand = new Hand(cards);
        //when
        int score = hand.calculateScore();
        //then
        assertThat(score).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> testCalculateScore() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        new Card(Suit.CLOVER, Value.JACK), new Card(Suit.DIAMOND, Value.FOUR),
                        new Card(Suit.HEART, Value.ACE)), 15),
                Arguments.of(Arrays.asList(
                        new Card(Suit.CLOVER, Value.JACK), new Card(Suit.DIAMOND, Value.TEN),
                        new Card(Suit.HEART, Value.ACE)), 21),
                Arguments.of(Arrays.asList(
                        new Card(Suit.CLOVER, Value.TWO), new Card(Suit.DIAMOND, Value.SEVEN),
                        new Card(Suit.HEART, Value.ACE)), 20),
                Arguments.of(Arrays.asList(
                        new Card(Suit.CLOVER, Value.KING), new Card(Suit.DIAMOND, Value.SIX),
                        new Card(Suit.HEART, Value.FOUR)), 20),
                Arguments.of(Arrays.asList(
                        new Card(Suit.CLOVER, Value.NINE), new Card(Suit.DIAMOND, Value.QUEEN),
                        new Card(Suit.HEART, Value.FIVE)), 24),
                Arguments.of(Arrays.asList(
                        new Card(Suit.CLOVER, Value.NINE), new Card(Suit.HEART, Value.TWO),
                        new Card(Suit.DIAMOND, Value.QUEEN), new Card(Suit.HEART, Value.ACE)), 22),
                Arguments.of(Arrays.asList(
                        new Card(Suit.CLOVER, Value.FIVE), new Card(Suit.HEART, Value.ACE),
                        new Card(Suit.DIAMOND, Value.TEN)), 16),
                Arguments.of(Arrays.asList(
                        new Card(Suit.CLOVER, Value.ACE), new Card(Suit.HEART, Value.ACE),
                        new Card(Suit.DIAMOND, Value.ACE), new Card(Suit.SPADE, Value.ACE)), 14)
        );
    }
}