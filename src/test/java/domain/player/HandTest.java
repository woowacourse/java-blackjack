package domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Card;
import domain.CardNumber;
import domain.Suit;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class HandTest {

    @Test
    void 카드숫자_2에서_10은_그대로_계산한다() {
        Hand hand = new Hand();
        List<Card> cards = List.of(
                new Card(Suit.SPADE, CardNumber.TWO),
                new Card(Suit.SPADE, CardNumber.THREE),
                new Card(Suit.SPADE, CardNumber.FOUR),
                new Card(Suit.SPADE, CardNumber.FIVE),
                new Card(Suit.SPADE, CardNumber.SIX),
                new Card(Suit.SPADE, CardNumber.SEVEN),
                new Card(Suit.SPADE, CardNumber.EIGHT),
                new Card(Suit.SPADE, CardNumber.NINE),
                new Card(Suit.SPADE, CardNumber.TEN));
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
                        new Card(Suit.SPADE, CardNumber.ACE)
                ), 11),

                Arguments.of(List.of(
                        new Card(Suit.SPADE, CardNumber.ACE),
                        new Card(Suit.CLOVER, CardNumber.ACE),
                        new Card(Suit.SPADE, CardNumber.TWO),
                        new Card(Suit.SPADE, CardNumber.THREE)
                ), 17),

                Arguments.of(List.of(
                        new Card(Suit.SPADE, CardNumber.TEN),
                        new Card(Suit.CLOVER, CardNumber.TEN),
                        new Card(Suit.HEART, CardNumber.TEN),
                        new Card(Suit.SPADE, CardNumber.ACE)
                ), 31),

                Arguments.of(List.of(
                        new Card(Suit.SPADE, CardNumber.TEN),
                        new Card(Suit.CLOVER, CardNumber.TEN),
                        new Card(Suit.SPADE, CardNumber.ACE)
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
                        new Card(Suit.SPADE, CardNumber.JACK)
                ), 10),
                Arguments.of(List.of(
                        new Card(Suit.SPADE, CardNumber.QUEEN)
                ), 10),
                Arguments.of(List.of(
                        new Card(Suit.SPADE, CardNumber.KING)
                ), 10),

                Arguments.of(List.of(
                        new Card(Suit.SPADE, CardNumber.ACE),
                        new Card(Suit.SPADE, CardNumber.TEN),
                        new Card(Suit.SPADE, CardNumber.JACK),
                        new Card(Suit.SPADE, CardNumber.QUEEN),
                        new Card(Suit.SPADE, CardNumber.KING)
                ), 41)
        );
    }

    @Test
    void 핸드_점수_총합이_21_초과인_경우_버스트_판정() {
        Hand hand = new Hand();
        List<Card> cards = List.of(
                new Card(Suit.SPADE, CardNumber.KING),
                new Card(Suit.SPADE, CardNumber.QUEEN),
                new Card(Suit.SPADE, CardNumber.TWO));
        for (Card card : cards) {
            hand.add(card);
        }

        Assertions.assertThat(hand.isBust()).isTrue();
    }

    @Test
    void 핸드_점수_총합이_21_이하인_경우_판정() {
        Hand hand = new Hand();
        List<Card> cards = List.of(
                new Card(Suit.SPADE, CardNumber.KING),
                new Card(Suit.SPADE, CardNumber.QUEEN),
                new Card(Suit.SPADE, CardNumber.ACE));
        for (Card card : cards) {
            hand.add(card);
        }

        Assertions.assertThat(hand.isBust()).isFalse();
    }
}

