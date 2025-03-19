package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Symbol;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class HandTest {

    @DisplayName("카드를 자신의 Hand에 추가할 수 있다.")
    @Test
    void add() {
        //given
        Card card = new Card(Symbol.COLVER, Rank.FIVE);
        Hand hand = new Hand();

        //when
        hand.hit(card);

        //then
        assertThat(hand.getCards()).isEqualTo(List.of(new Card(Symbol.COLVER, Rank.FIVE)));
    }

    @DisplayName("자신의 카드 점수를 계산한다.")
    @Test
    void calculateTotalScore() {
        //given
        Card card1 = new Card(Symbol.COLVER, Rank.FIVE);
        Card card2 = new Card(Symbol.COLVER, Rank.FIVE);
        Hand hand = new Hand();

        hand.hit(card1);
        hand.hit(card2);

        //when
        int score = hand.calculateTotalScore();

        //then
        assertThat(score).isEqualTo(10);
    }

    @DisplayName("2장의 카드가 21점이라면 블랙잭이다.")
    @Test
    void isBlackjack() {
        //given
        List<Card> cards = List.of(
                new Card(Symbol.COLVER, Rank.JACK),
                new Card(Symbol.SPADE, Rank.ACE)
        );

        Hand hand = new Hand();

        for (Card card : cards) {
            hand.hit(card);
        }

        //when
        boolean actual = hand.isBlackjack();

        //then
        assertThat(actual).isTrue();
    }

    @DisplayName("점수가 21점을 초과하면 버스트된다.")
    @Test
    void bust() {
        //given
        List<Card> cards = List.of(
                new Card(Symbol.HEART, Rank.KING),
                new Card(Symbol.COLVER, Rank.KING),
                new Card(Symbol.DIAMOND, Rank.TWO));

        Hand hand = new Hand();

        for (Card card : cards) {
            hand.hit(card);
        }
        //when
        boolean actual = hand.isBust();

        //then
        assertThat(actual).isTrue();
    }

    @DisplayName("점수가 21점을 초과하지않으면 버스트되지 않는다.")
    @Test
    void notBust() {
        //given
        List<Card> cards = List.of(
                new Card(Symbol.HEART, Rank.KING),
                new Card(Symbol.HEART, Rank.JACK));

        Hand hand = new Hand();

        for (Card card : cards) {
            hand.hit(card);
        }

        //when
        boolean actual = hand.isBust();

        //then
        assertThat(actual).isFalse();
    }

    @Nested
    @DisplayName("Ace 개수에 따른 점수 계산을 한다.")
    class considerAceTest {
        @DisplayName("Ace가 있을 때, 11로 간주해도 21을 초과하지않을 경우 11로 간주한다.")
        @Test
        void considerAceHas11() {
            //given
            List<Card> cards = List.of(
                    new Card(Symbol.COLVER, Rank.ACE),
                    new Card(Symbol.COLVER, Rank.KING)
            );

            Hand hand = new Hand();

            for (Card card : cards) {
                hand.hit(card);
            }

            //when
            int actual = hand.calculateTotalScore();

            //then
            assertThat(actual).isEqualTo(21);
        }

        @DisplayName("Ace가 두장 있을 때, 합계가 21을 초과하지않을 경우 Ace 한장의 점수를 1로 간주한다.")
        @Test
        void considerAceHas1() {
            //given
            List<Card> cards = List.of(
                    new Card(Symbol.COLVER, Rank.ACE),
                    new Card(Symbol.COLVER, Rank.ACE)
            );

            Hand hand = new Hand();
            for (Card card : cards) {
                hand.hit(card);
            }

            //when
            int actual = hand.calculateTotalScore();

            //then
            assertThat(actual).isEqualTo(12);
        }
    }

}
