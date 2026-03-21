package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class HandTest {

    @Test
    void 카드숫자_2에서_10은_그대로_계산한다() {
        Hand hand = new Hand();
        List<Card> cards = List.of(
                new Card(Rank.TWO, Suit.SPADE),
                new Card(Rank.THREE, Suit.SPADE),
                new Card(Rank.FOUR, Suit.SPADE),
                new Card(Rank.FIVE, Suit.SPADE),
                new Card(Rank.SIX, Suit.SPADE),
                new Card(Rank.SEVEN, Suit.SPADE),
                new Card(Rank.EIGHT, Suit.SPADE),
                new Card(Rank.NINE, Suit.SPADE),
                new Card(Rank.TEN, Suit.SPADE));
        for (Card card : cards) {
            hand.add(card);
        }

        int score = hand.calculateScore();

        assertThat(score).isEqualTo(54);
    }

    @ParameterizedTest
    @MethodSource("provideAceCases")
    void Ace는_1_또는_11로_계산한다(List<Card> cards, int expected) {
        Hand hand = new Hand();
        for (Card card : cards) {
            hand.add(card);
        }

        int actual = hand.calculateScore();

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideAceCases() {
        return Stream.of(
                Arguments.of(List.of(
                        new Card(Rank.ACE, Suit.SPADE)
                ), 11),

                Arguments.of(List.of(
                        new Card(Rank.ACE, Suit.SPADE),
                        new Card(Rank.ACE, Suit.CLOVER),
                        new Card(Rank.TWO, Suit.SPADE),
                        new Card(Rank.THREE, Suit.SPADE)
                ), 17),

                Arguments.of(List.of(
                        new Card(Rank.TEN, Suit.SPADE),
                        new Card(Rank.TEN, Suit.CLOVER),
                        new Card(Rank.TEN, Suit.HEART),
                        new Card(Rank.ACE, Suit.SPADE)
                ), 31),

                Arguments.of(List.of(
                        new Card(Rank.TEN, Suit.SPADE),
                        new Card(Rank.TEN, Suit.CLOVER),
                        new Card(Rank.ACE, Suit.SPADE)
                ), 21)
        );
    }

    @ParameterizedTest
    @MethodSource("provideFaceCardCases")
    void J_Q_K는_각각_10으로_계산한다(List<Card> cards, int expected) {
        Hand hand = new Hand();
        for (Card card : cards) {
            hand.add(card);
        }

        int actual = hand.calculateScore();

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideFaceCardCases() {
        return Stream.of(
                Arguments.of(List.of(
                        new Card(Rank.JACK, Suit.SPADE)
                ), 10),
                Arguments.of(List.of(
                        new Card(Rank.QUEEN, Suit.SPADE)
                ), 10),
                Arguments.of(List.of(
                        new Card(Rank.KING, Suit.SPADE)
                ), 10),

                Arguments.of(List.of(
                        new Card(Rank.ACE, Suit.SPADE),
                        new Card(Rank.TEN, Suit.SPADE),
                        new Card(Rank.JACK, Suit.SPADE),
                        new Card(Rank.QUEEN, Suit.SPADE),
                        new Card(Rank.KING, Suit.SPADE)
                ), 41)
        );
    }
}

