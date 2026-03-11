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
                new Card(Suit.SPADE, Rank.TWO),
                new Card(Suit.SPADE, Rank.THREE),
                new Card(Suit.SPADE, Rank.FOUR),
                new Card(Suit.SPADE, Rank.FIVE),
                new Card(Suit.SPADE, Rank.SIX),
                new Card(Suit.SPADE, Rank.SEVEN),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.NINE),
                new Card(Suit.SPADE, Rank.TEN));
        for (Card card : cards) {
            hand.add(card);
        }

        int actual = hand.calculateScore().value();

        assertThat(actual).isEqualTo(54);
    }

    @ParameterizedTest
    @MethodSource("provideAceCases")
    void Ace는_1_또는_11로_계산한다(List<Card> cards, int expected) {
        Hand hand = new Hand();
        for (Card card : cards) {
            hand.add(card);
        }

        int actual = hand.calculateScore().value();

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideAceCases() {
        return Stream.of(
                Arguments.of(List.of(
                        new Card(Suit.SPADE, Rank.ACE)
                ), 11),

                Arguments.of(List.of(
                        new Card(Suit.SPADE, Rank.ACE),
                        new Card(Suit.CLUB, Rank.ACE),
                        new Card(Suit.SPADE, Rank.TWO),
                        new Card(Suit.SPADE, Rank.THREE)
                ), 17),

                Arguments.of(List.of(
                        new Card(Suit.SPADE, Rank.TEN),
                        new Card(Suit.CLUB, Rank.TEN),
                        new Card(Suit.HEART, Rank.TEN),
                        new Card(Suit.SPADE, Rank.ACE)
                ), 31),

                Arguments.of(List.of(
                        new Card(Suit.SPADE, Rank.TEN),
                        new Card(Suit.CLUB, Rank.TEN),
                        new Card(Suit.SPADE, Rank.ACE)
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

        int actual = hand.calculateScore().value();

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideFaceCardCases() {
        return Stream.of(
                Arguments.of(List.of(
                        new Card(Suit.SPADE, Rank.JACK)
                ), 10),
                Arguments.of(List.of(
                        new Card(Suit.SPADE, Rank.QUEEN)
                ), 10),
                Arguments.of(List.of(
                        new Card(Suit.SPADE, Rank.KING)
                ), 10),

                Arguments.of(List.of(
                        new Card(Suit.SPADE, Rank.ACE),
                        new Card(Suit.SPADE, Rank.TEN),
                        new Card(Suit.SPADE, Rank.JACK),
                        new Card(Suit.SPADE, Rank.QUEEN),
                        new Card(Suit.SPADE, Rank.KING)
                ), 41)
        );
    }
}

