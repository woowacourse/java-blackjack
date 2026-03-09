package blackjack.domain.hand;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    @Test
    @DisplayName("ACE가 없을 때 카드 숫자의 합을 점수로 반환한다")
    void calculateScore_returnsSumOfCardValues_whenNoAce() {
        // given
        Hand hand = new Hand();
        hand.add(new Card(Suit.HEART, Rank.SEVEN));
        hand.add(new Card(Suit.SPADE, Rank.EIGHT));

        // when
        int score = hand.calculateScore().getValue();

        // then
        assertThat(score).isEqualTo(15);
    }

    @Test
    @DisplayName("ACE를 11로 계산해도 21 이하이면 ACE를 11로 계산한다")
    void calculateScore_countsAceAsEleven_whenTotalDoesNotExceedTwentyOne() {
        // given
        Hand hand = new Hand();
        hand.add(new Card(Suit.HEART, Rank.ACE));
        hand.add(new Card(Suit.SPADE, Rank.EIGHT));

        // when
        int score = hand.calculateScore().getValue();

        // then
        assertThat(score).isEqualTo(19);
    }

    @Test
    @DisplayName("ACE를 11로 계산하면 21 초과일 때 ACE를 1로 계산한다")
    void calculateScore_countsAceAsOne_whenTotalExceedsTwentyOne() {
        // given
        Hand hand = new Hand();
        hand.add(new Card(Suit.HEART, Rank.ACE));
        hand.add(new Card(Suit.SPADE, Rank.EIGHT));
        hand.add(new Card(Suit.CLOVER, Rank.SEVEN));

        // when
        int score = hand.calculateScore().getValue();

        // then
        assertThat(score).isEqualTo(16);
    }

    @Test
    @DisplayName("점수가 21 이하이면 버스트가 아니다")
    void isBust_returnsFalse_whenScoreIsEqualToTwentyOne() {
        // given
        Hand hand = new Hand();
        hand.add(new Card(Suit.HEART, Rank.TEN));
        hand.add(new Card(Suit.SPADE, Rank.EIGHT));
        hand.add(new Card(Suit.CLOVER, Rank.THREE));

        // when
        boolean result = hand.isBust();

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("점수가 21 초과이면 버스트이다")
    void isBust_returnsTrue_whenScoreExceedsTwentyOne() {
        // given
        Hand hand = new Hand();
        hand.add(new Card(Suit.HEART, Rank.TEN));
        hand.add(new Card(Suit.SPADE, Rank.EIGHT));
        hand.add(new Card(Suit.CLOVER, Rank.FOUR));

        // when
        boolean result = hand.isBust();

        // then
        assertThat(result).isTrue();
    }
}
