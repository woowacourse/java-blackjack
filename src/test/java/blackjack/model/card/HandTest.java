package blackjack.model.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    @Test
    @DisplayName("Hand가 블랙잭일 경우 true 반환")
    void isBlackjack_return_true() {
        //given
        Hand hand = new Hand();
        hand.draw(new Card(Rank.J, Suit.DIAMOND));
        hand.draw(new Card(Rank.ACE, Suit.DIAMOND));

        //when & then
        assertThat(hand.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("Hand가 블랙잭이 아닐 경우 false 반환")
    void isBlackjack_return_false() {
        //given
        Hand hand = new Hand();
        hand.draw(new Card(Rank.J, Suit.DIAMOND));
        hand.draw(new Card(Rank.K, Suit.DIAMOND));

        //when & then
        assertThat(hand.isBlackjack()).isFalse();
    }

    @Test
    @DisplayName("Hand가 버스트일 경우 true 반환")
    void isBust_return_true() {
        //given
        Hand hand = new Hand();
        hand.draw(new Card(Rank.J, Suit.DIAMOND));
        hand.draw(new Card(Rank.Q, Suit.DIAMOND));
        hand.draw(new Card(Rank.TWO, Suit.DIAMOND));

        //when & then
        assertThat(hand.isBust()).isTrue();
    }

    @Test
    @DisplayName("Hand가 버스트가 아닐 경우 false 반환")
    void isBust_return_false() {
        //given
        Hand hand = new Hand();
        hand.draw(new Card(Rank.J, Suit.DIAMOND));
        hand.draw(new Card(Rank.Q, Suit.DIAMOND));
        hand.draw(new Card(Rank.ACE, Suit.DIAMOND));

        //when & then
        assertThat(hand.isBust()).isFalse();
    }
}