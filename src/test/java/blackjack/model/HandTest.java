package blackjack.model;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    private Hand hand;

    @BeforeEach
    void setup() {
        hand = new Hand();
    }

    @Test
    @DisplayName("ACE가 없을 때 점수 합계")
    void test_score_without_ace() {
        hand.addCard(new Card(Suit.HEART, Rank.JACK));
        hand.addCard(new Card(Suit.CLOVER, Rank.EIGHT));

        assertThat(hand.calculateScore()).isEqualTo(18);
    }

    @Test
    @DisplayName("11점을 더할 때 21을 넘지 않으면 ACE는 11로 계산")
    void test_ace_as_11_when_under_21() {
        hand.addCard(new Card(Suit.HEART, Rank.JACK));
        hand.addCard(new Card(Suit.CLOVER, Rank.ACE));

        // JACK(10) + ACE(11) = 21
        assertThat(hand.calculateScore()).isEqualTo(21);
    }


    @Test
    @DisplayName("11점을 더할 때 21을 넘으면 ACE는 1점으로 계산")
    void test_ace_as_1_when_over_21() {
        hand.addCard(new Card(Suit.HEART, Rank.JACK));
        hand.addCard(new Card(Suit.CLOVER, Rank.EIGHT));
        hand.addCard(new Card(Suit.DIAMOND, Rank.ACE));

        // JACK(10) + EIGHT(8) + ACE(1) = 19
        assertThat(hand.calculateScore()).isEqualTo(19);
    }


    @Test
    @DisplayName("카드 2장으로 21점이면 블랙잭")
    void test_blackjack_whit_two_cards() {
        hand.addCard(new Card(Suit.HEART, Rank.JACK));
        hand.addCard(new Card(Suit.CLOVER, Rank.ACE));

        assertThat(hand.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("카드 3장으로 21점이면 블랙잭 아님")
    void test_not_blackjack_whit_three_cards() {
        hand.addCard(new Card(Suit.HEART, Rank.JACK));
        hand.addCard(new Card(Suit.CLOVER, Rank.NINE));
        hand.addCard(new Card(Suit.CLOVER, Rank.TWO));

        assertThat(hand.isBlackjack()).isFalse();
    }

    @Test
    @DisplayName("21점을 초과하면 Bust")
    void test_is_bust_over_21() {
        hand.addCard(new Card(Suit.HEART, Rank.JACK));
        hand.addCard(new Card(Suit.CLOVER, Rank.NINE));
        hand.addCard(new Card(Suit.CLOVER, Rank.THREE));

        assertThat(hand.isBust()).isTrue();
    }

    @Test
    @DisplayName("21점 이하면 Bust가 아님")
    void test_not_bust_at_21() {
        hand.addCard(new Card(Suit.HEART, Rank.JACK));
        hand.addCard(new Card(Suit.CLOVER, Rank.NINE));
        hand.addCard(new Card(Suit.CLOVER, Rank.TWO));

        assertThat(hand.isBust()).isFalse();
    }
}
