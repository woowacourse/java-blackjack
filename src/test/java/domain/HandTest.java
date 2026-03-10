package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {

    @Test
    void 합계가_21점이면_블랙잭이다() {
        Hand hand = new Hand();
        hand.add(new Card(Rank.ACE, Suit.SPADE));
        hand.add(new Card(Rank.KING, Suit.HEART));

        assertThat(hand.isBlackjack()).isTrue();
    }

    @Test
    void 합계가_21점이_아니면_블랙잭이_아니다() {
        Hand hand = new Hand();
        hand.add(new Card(Rank.TEN, Suit.SPADE));
        hand.add(new Card(Rank.KING, Suit.HEART));

        assertThat(hand.isBlackjack()).isFalse();
    }
}