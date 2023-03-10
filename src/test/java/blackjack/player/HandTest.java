package blackjack.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import card.Card;
import card.Rank;
import card.Suit;
import participants.Hand;

class HandTest {
    Hand hand;

    @Test
    @DisplayName("생성자 테스트")
    void create() {
        assertThatCode(Hand::new)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Hand에 추가한 카드가 카드리스트에 들어있다.")
    void saveCard() {
        hand = new Hand();
        Card card = new Card(Rank.ACE, Suit.CLOVER);
        hand.add(card);

        assertThat(hand.getCards()).contains(card);
    }

    @Test
    @DisplayName("카드들의 점수를 계산한다.")
    void calculateScore() {
        hand = new Hand();
        Card card = new Card(Rank.TWO, Suit.CLOVER);
        Card card2 = new Card(Rank.EIGHT, Suit.HEART);
        hand.add(card);
        hand.add(card2);

        int score = hand.calculateScore().getScore();

        assertThat(score).isEqualTo(10);
    }

    @Test
    @DisplayName("첫번째 카드를 가져온다")
    void pickFirstCard() {
        hand = new Hand();
        Card card = new Card(Rank.ACE, Suit.HEART);
        Card card1 = new Card(Rank.TEN, Suit.HEART);
        Card card2 = new Card(Rank.FIVE, Suit.HEART);
        hand.add(card);
        hand.add(card1);
        hand.add(card2);

        Card card3 = hand.pickFirstCard();

        assertThat(card3).isEqualTo(card);
    }

    @Test
    @DisplayName("첫번째 카드를 가져올 때 hand가 비어있으면 예외를 던진다.")
    void pickFirstCardFail() {
        hand = new Hand();

        assertThatThrownBy(() -> hand.pickFirstCard())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("들고있는 카드가 없습니다.");

    }

    @DisplayName("에이스가 있을 때")
    @Nested
    class AceTest {
        @DisplayName("합계가 11 이하이면 에이스가 11로 계산된다.")
        @Test
        void underEleven() {
            hand = new Hand();
            Card card = new Card(Rank.ACE, Suit.HEART);
            Card card1 = new Card(Rank.TWO, Suit.HEART);
            hand.add(card);
            hand.add(card1);

            int score = hand.calculateScore().getScore();
            assertThat(score).isEqualTo(13);
        }

        @DisplayName("합계가 11 초과이면 에이스가 1로 계산된다.")
        @Test
        void upperEleven() {
            hand = new Hand();
            Card card = new Card(Rank.ACE, Suit.HEART);
            Card card1 = new Card(Rank.TEN, Suit.HEART);
            Card card2 = new Card(Rank.FIVE, Suit.HEART);
            hand.add(card);
            hand.add(card1);
            hand.add(card2);

            int score = hand.calculateScore().getScore();
            assertThat(score).isEqualTo(16);
        }

        @Test
        @DisplayName("블랙잭인 경우 true를 반환한다.")
        void isBlackjack() {
            hand = new Hand();
            Card card = new Card(Rank.ACE, Suit.HEART);
            Card card1 = new Card(Rank.TEN, Suit.HEART);
            hand.add(card);
            hand.add(card1);

            assertThat(hand.isBlackjack()).isTrue();
        }

        @Test
        @DisplayName("블랙잭이 아닌 경우 false를 반환한다.")
        void isBlackjackFalse() {
            hand = new Hand();
            Card card = new Card(Rank.ACE, Suit.HEART);
            Card card1 = new Card(Rank.TEN, Suit.HEART);
            Card card2 = new Card(Rank.TEN, Suit.SPADE);
            hand.add(card);
            hand.add(card1);
            hand.add(card2);

            assertThat(hand.isBlackjack()).isFalse();
        }
    }
}
