package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {
    
    @Test
    @DisplayName("ACE가 없는 손패의 점수를 정확히 합산한다.")
    void getTotalScore_WithoutAce() {
        Hand hand = new Hand();
        hand.addCards(List.of(
                new Card(Rank.TEN, Suit.SPADE),
                new Card(Rank.EIGHT, Suit.HEART)
        ));
        assertThat(hand.getTotalScore()).isEqualTo(18);
    }
    
    @Test
    @DisplayName("ACE를 11로 계산했을 때 21 이하이면 11로 계산한다.")
    void getTotalScore_WithAce_AsEleven() {
        Hand hand = new Hand();
        hand.addCards(List.of(
                new Card(Rank.ACE, Suit.SPADE),
                new Card(Rank.NINE, Suit.HEART)
        ));
        assertThat(hand.getTotalScore()).isEqualTo(20);
    }
    
    @Test
    @DisplayName("ACE를 11로 계산했을 때 21을 초과하면 1로 계산한다.")
    void getTotalScore_WithAce_AsOne_WhenExceedBustScore() {
        Hand hand = new Hand();
        hand.addCards(List.of(
                new Card(Rank.ACE, Suit.SPADE),
                new Card(Rank.TEN, Suit.HEART),
                new Card(Rank.NINE, Suit.DIAMOND)
        ));
        assertThat(hand.getTotalScore()).isEqualTo(20);
    }
    
    @Test
    @DisplayName("손패의 총합이 21점이면 블랙잭 상태로 판별한다.")
    void isBlackjack_True_WhenScoreIsExactly21() {
        Hand hand = new Hand();
        hand.addCards(List.of(
                new Card(Rank.ACE, Suit.SPADE),
                new Card(Rank.KING, Suit.HEART)
        ));
        assertThat(hand.isBlackjack()).isTrue();
    }
    
    @Test
    @DisplayName("손패의 총합이 21점을 초과하면 버스트 상태로 판별한다.")
    void isBust_True_WhenScoreExceeds21() {
        Hand hand = new Hand();
        hand.addCards(List.of(
                new Card(Rank.TEN, Suit.SPADE),
                new Card(Rank.TEN, Suit.HEART),
                new Card(Rank.TWO, Suit.DIAMOND)
        ));
        assertThat(hand.isBust()).isTrue();
    }
}
