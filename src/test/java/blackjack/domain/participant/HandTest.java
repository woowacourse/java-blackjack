package blackjack.domain.participant;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.FixtureCard.*;
import static org.assertj.core.api.Assertions.assertThat;

class HandTest {

    @DisplayName("카드 한 장을 손 패로 가지고 온다.")
    @Test
    void add() {
        Hand hand = new Hand();
        Card addCard = TWO_HEART;

        hand.add(addCard);

        assertThat(hand.getCards()).contains(addCard);
    }

    @DisplayName("손에 들고 있는 카드의 합계를 반환한다.")
    @Test
    void calculateSum() {
        Hand hand = new Hand();
        hand.add(List.of(SEVEN_HEART, TEN_HEART));

        int actual = hand.calculateScore();
        int expected = SEVEN_HEART.getRankValue() + TEN_HEART.getRankValue();

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("손 패에 Ace카드가 몇개 있는지 반환한다.")
    @Test
    void containAce() {
        Hand hand = new Hand();
        hand.add(List.of(ACE_HEART, TEN_HEART));

        int actual = hand.countAceCard();

        assertThat(actual).isEqualTo(1);
    }

    @Nested
    @DisplayName("손패의 점수를 계산한다.")
    class calculateScore {

        @DisplayName("블랙잭인 경우")
        @Test
        void blackJack() {
            Hand hand = new Hand();
            List<Card> cards = List.of(
                    ACE_HEART,
                    TEN_HEART
            );
            hand.add(cards);

            int score = hand.calculateScore();

            assertThat(score).isEqualTo(21);
        }

        @DisplayName("A가 하나 있고, A를 1로 계산해야 하는 경우")
        @Test
        void oneAceOneRegard() {
            Hand hand = new Hand();
            List<Card> cards = List.of(
                    ACE_HEART,
                    TEN_HEART,
                    TEN_HEART
            );
            hand.add(cards);

            int score = hand.calculateScore();

            assertThat(score).isEqualTo(21);
        }

        @DisplayName("A가 두개 이상이고, 모두 1로 계산해야 하는 경우")
        @Test
        void TwoAceTwoRegard() {
            Hand hand = new Hand();
            List<Card> cards = List.of(
                    ACE_HEART,
                    ACE_HEART,
                    TEN_HEART
            );
            hand.add(cards);

            int score = hand.calculateScore();

            assertThat(score).isEqualTo(12);
        }

        @DisplayName("A가 두개 이상이고, 하나만 11로 계산해야 하는 경우")
        @Test
        void ThreeAceTwoRegard() {
            Hand hand = new Hand();
            List<Card> cards = List.of(
                    ACE_HEART,
                    ACE_HEART,
                    TWO_HEART
            );
            hand.add(cards);

            int score = hand.calculateScore();

            assertThat(score).isEqualTo(14);
        }

        @DisplayName("A가 없는 경우 - 버스트")
        @Test
        void NoAceBust() {
            Hand hand = new Hand();
            List<Card> cards = List.of(
                    TEN_HEART,
                    TEN_HEART,
                    TWO_HEART
            );
            hand.add(cards);

            int score = hand.calculateScore();

            assertThat(score).isEqualTo(22);
        }

        @DisplayName("A가 없는 경우 - 버스트아님")
        @Test
        void NoAceNotBust() {
            Hand hand = new Hand();
            List<Card> cards = List.of(
                    TEN_HEART,
                    TEN_HEART
            );
            hand.add(cards);

            int score = hand.calculateScore();

            assertThat(score).isEqualTo(20);
        }

        @DisplayName("A가 있으면서, A를 1로 생각해도 버스트가 나는 경우")
        @Test
        void HasAceBust() {
            Hand hand = new Hand();
            List<Card> cards = List.of(
                    ACE_HEART,
                    TWO_HEART,
                    TEN_HEART,
                    TEN_HEART
            );
            hand.add(cards);

            int score = hand.calculateScore();

            assertThat(score).isEqualTo(23);
        }
    }
}
