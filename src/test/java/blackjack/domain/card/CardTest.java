package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {
    
    @Test
    @DisplayName("카드는 자신의 랭크에 해당하는 점수를 반환한다.")
    void returnScoreByRank() {
        Card card = new Card(Rank.SEVEN, Suit.SPADES);
        assertThat(card.getScore()).isEqualTo(7);
    }
    
    @Test
    @DisplayName("카드의 랭크가 ACE일 경우 true를 반환한다.")
    void isAce_True_WhenRankIsAce() {
        Card aceCard = new Card(Rank.ACE, Suit.HEARTS);
        assertThat(aceCard.isAce()).isTrue();
    }
    
    @Test
    @DisplayName("카드의 랭크가 ACE가 아닐 경우 false를 반환한다.")
    void isAce_False_WhenRankIsNotAce() {
        Card normalCard = new Card(Rank.KING, Suit.HEARTS);
        assertThat(normalCard.isAce()).isFalse();
    }
}
