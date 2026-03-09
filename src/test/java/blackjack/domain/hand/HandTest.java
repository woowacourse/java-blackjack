package blackjack.domain.hand;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {

    @Test
    void ACE가_없을_때_카드_숫자의_합을_점수로_반환한다() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.HEART, Rank.SEVEN));
        hand.add(new Card(Suit.SPADE, Rank.EIGHT));

        assertThat(hand.calculateScore().value()).isEqualTo(15);
    }

    @Test
    void ACE를_11로_계산해도_21_이하이면_ACE를_11로_계산한다() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.HEART, Rank.ACE));
        hand.add(new Card(Suit.SPADE, Rank.EIGHT));

        assertThat(hand.calculateScore().value()).isEqualTo(19);
    }

    @Test
    void ACE를_11로_계산하면_21_초과일_때_ACE를_1로_계산한다() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.HEART, Rank.ACE));
        hand.add(new Card(Suit.SPADE, Rank.EIGHT));
        hand.add(new Card(Suit.CLOVER, Rank.SEVEN));

        assertThat(hand.calculateScore().value()).isEqualTo(16);
    }

    @Test
    void 점수가_21_이하이면_버스트가_아니다() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.HEART, Rank.TEN));
        hand.add(new Card(Suit.SPADE, Rank.EIGHT));
        hand.add(new Card(Suit.CLOVER, Rank.THREE));

        assertThat(hand.isBust()).isFalse();
    }

    @Test
    void 점수가_21_초과이면_버스트이다() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.HEART, Rank.TEN));
        hand.add(new Card(Suit.SPADE, Rank.EIGHT));
        hand.add(new Card(Suit.CLOVER, Rank.FOUR));

        assertThat(hand.isBust()).isTrue();
    }
}
