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
        hand.add(new Card(Suit.CLUB, Rank.SEVEN));

        assertThat(hand.calculateScore().value()).isEqualTo(16);
    }

    @Test
    void 점수가_21_이하이면_버스트가_아니다() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.HEART, Rank.TEN));
        hand.add(new Card(Suit.SPADE, Rank.EIGHT));
        hand.add(new Card(Suit.CLUB, Rank.THREE));

        assertThat(hand.isBust()).isFalse();
    }

    @Test
    void 점수가_21_초과이면_버스트이다() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.HEART, Rank.TEN));
        hand.add(new Card(Suit.SPADE, Rank.EIGHT));
        hand.add(new Card(Suit.CLUB, Rank.FOUR));

        assertThat(hand.isBust()).isTrue();
    }

    @Test
    void 두_장의_카드와_카드의_합이_21이면_블랙잭이다() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.HEART, Rank.ACE));
        hand.add(new Card(Suit.SPADE, Rank.KING));

        assertThat(hand.isBlackjack()).isTrue();
    }

    @Test
    void 경계_값인_세_장의_카드와_카드의_합이_21이면_블랙잭이_아니다() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.HEART, Rank.ACE));
        hand.add(new Card(Suit.SPADE, Rank.FOUR));
        hand.add(new Card(Suit.DIAMOND, Rank.SIX));

        assertThat(hand.isBlackjack()).isFalse();
    }

    @Test
    void 한_장의_카드만_찾으면_숫자와_문양을_합친_문자열이_나온다() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.HEART, Rank.ACE));
        hand.add(new Card(Suit.SPADE, Rank.FOUR));
        hand.add(new Card(Suit.DIAMOND, Rank.SIX));

        assertThat(hand.getFirstCard()).isEqualTo("A하트");
    }
}
