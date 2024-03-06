package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {

    @DisplayName("손에 들고 있는 카드의 합계를 반환한다.")
    @Test
    void calculateSum() {
        Card card1 = new Card(Letter.SEVEN, Mark.CLOVER);
        Card card2 = new Card(Letter.K, Mark.SPADE);
        List<Card> cards = List.of(card1, card2);

        Hand hand = new Hand(cards);

        assertThat(hand.calculateSum())
                .isEqualTo(card1.getValue() + card2.getValue());
    }

    @DisplayName("카드 한 장을 손 패로 가지고 온다.")
    @Test
    void add() {
        Card card1 = new Card(Letter.SEVEN, Mark.CLOVER);
        Card card2 = new Card(Letter.K, Mark.SPADE);
        List<Card> cards = new ArrayList<>(List.of(card1, card2));
        Hand hand = new Hand(cards);
        Card addCard = new Card(Letter.J, Mark.DIAMOND);

        hand.add(addCard);

        assertThat(hand.getCards()).contains(addCard);
    }

    @DisplayName("손 패에 Ace카드가 몇개 있는지 반환한다.")
    @Test
    void containAce() {
        List<Card> hasAceCard = List.of(new Card(Letter.A, Mark.SPADE), new Card(Letter.J, Mark.SPADE));
        Hand hasAceHand = new Hand(hasAceCard);
        int actual1 = hasAceHand.countAceCard();

        assertThat(actual1).isEqualTo(1);
    }

    @Nested
    @DisplayName("손패의 점수를 계산한다.")
    class calculateScore {

        @DisplayName("블랙잭인 경우")
        @Test
        void blackJack() {
            List<Card> cards = List.of(
                    new Card(Letter.A, Mark.SPADE),
                    new Card(Letter.J, Mark.SPADE));

            Hand hand = new Hand(cards);
            int score = hand.calculateScore();

            assertThat(score).isEqualTo(21);
        }

        @DisplayName("A가 하나 있고, A를 1로 계산해야 하는 경우")
        @Test
        void oneAceOneRegard() {
            List<Card> cards = List.of(
                    new Card(Letter.A, Mark.SPADE),
                    new Card(Letter.J, Mark.SPADE),
                    new Card(Letter.K, Mark.SPADE)
            );

            Hand hand = new Hand(cards);
            int score = hand.calculateScore();

            assertThat(score).isEqualTo(21);
        }

        @DisplayName("A가 두개 있고, A를 둘 다 1로 계산해야 하는 경우")
        @Test
        void TwoAceTwoRegard() {
            List<Card> cards = List.of(
                    new Card(Letter.A, Mark.SPADE),
                    new Card(Letter.A, Mark.SPADE),
                    new Card(Letter.K, Mark.SPADE)
            );

            Hand hand = new Hand(cards);
            int score = hand.calculateScore();

            assertThat(score).isEqualTo(12);
        }

        @DisplayName("A가 세개 있고, 하나만 11로 계산해야 하는 경우")
        @Test
        void ThreeAceTwoRegard() {
            List<Card> cards = List.of(
                    new Card(Letter.A, Mark.SPADE),
                    new Card(Letter.A, Mark.SPADE),
                    new Card(Letter.A, Mark.SPADE),
                    new Card(Letter.TWO, Mark.SPADE)
            );

            Hand hand = new Hand(cards);
            int score = hand.calculateScore();

            assertThat(score).isEqualTo(15);
        }

        @DisplayName("A가 없는 경우 - 버스트")
        @Test
        void NoAceBust() {
            List<Card> cards = List.of(
                    new Card(Letter.K, Mark.SPADE),
                    new Card(Letter.K, Mark.SPADE),
                    new Card(Letter.K, Mark.SPADE)
            );

            Hand hand = new Hand(cards);
            int score = hand.calculateScore();

            assertThat(score).isEqualTo(30);
        }

        @DisplayName("A가 없는 경우 - 버스트아님")
        @Test
        void NoAceNotBust() {
            List<Card> cards = List.of(
                    new Card(Letter.J, Mark.SPADE),
                    new Card(Letter.K, Mark.SPADE)
            );

            Hand hand = new Hand(cards);
            int score = hand.calculateScore();

            assertThat(score).isEqualTo(20);
        }

        @DisplayName("A가 있으면서, A를 1로 생각해도 버스트가 나는 경우")
        @Test
        void HasAceBust() {
            List<Card> cards = List.of(
                    new Card(Letter.J, Mark.SPADE),
                    new Card(Letter.A, Mark.SPADE),
                    new Card(Letter.EIGHT, Mark.SPADE),
                    new Card(Letter.K, Mark.SPADE)
            );

            Hand hand = new Hand(cards);
            int score = hand.calculateScore();

            assertThat(score).isEqualTo(29);
        }

    }
}
